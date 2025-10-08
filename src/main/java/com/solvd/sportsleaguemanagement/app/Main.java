package com.solvd.sportsleaguemanagement.app;

import com.solvd.sportsleaguemanagement.common.Money;
import com.solvd.sportsleaguemanagement.common.Result;
import com.solvd.sportsleaguemanagement.common.annotations.Auditable;
import com.solvd.sportsleaguemanagement.contracts.Bookable;
import com.solvd.sportsleaguemanagement.event.Event;
import com.solvd.sportsleaguemanagement.event.Match;
import com.solvd.sportsleaguemanagement.event.Schedule;
import com.solvd.sportsleaguemanagement.exception.OverbookingException;
import com.solvd.sportsleaguemanagement.facility.Facility;
import com.solvd.sportsleaguemanagement.facility.Stadium;
import com.solvd.sportsleaguemanagement.facility.TrainingGround;
import com.solvd.sportsleaguemanagement.organization.IdGenerator;
import com.solvd.sportsleaguemanagement.organization.League;
import com.solvd.sportsleaguemanagement.organization.Repository;
import com.solvd.sportsleaguemanagement.organization.Team;
import com.solvd.sportsleaguemanagement.person.Coach;
import com.solvd.sportsleaguemanagement.person.Player;
import com.solvd.sportsleaguemanagement.person.Referee;
import com.solvd.sportsleaguemanagement.services.*;
import com.solvd.sportsleaguemanagement.types.Currency;
import com.solvd.sportsleaguemanagement.types.Position;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.function.*;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        URL url = Main.class.getResource("/book.txt");
        if (url != null) {
            String text = FileUtils.readFileToString(new File(url.toURI()), StandardCharsets.UTF_8);
            String[] words = StringUtils.split(StringUtils.lowerCase(text).replaceAll("[^\\p{L}\\p{Nd}]+", " "));
            long unique = Arrays.stream(words).filter(StringUtils::isNotBlank).distinct().count();
            FileUtils.writeStringToFile(new File("target/unique-words.txt"), "Unique words: " + unique, StandardCharsets.UTF_8);
        }

        Coach coach = createCoach();
        List<Player> players = createPlayers();
        List<Referee> officials = createReferees();
        List<Team> teams = createTeams(coach, players);
        League league = createLeague(teams);
        Stadium stadium = createStadium();
        TrainingGround training = createTrainingGround();
        Schedule schedule = createSchedule(league);
        Match match = createMatch(teams.get(0), teams.get(1), stadium, officials, players);

        Repository<Team> teamRepo = new Repository<>();
        Repository<Player> playerRepo = new Repository<>();
        teams.forEach(teamRepo::add);
        players.forEach(playerRepo::add);

        LOGGER.info("Team repo size: {}", teamRepo.size());
        LOGGER.info("Player repo empty? {}", playerRepo.isEmpty());

        BookingService booking = new BookingService();
        booking.addEvent(schedule);
        booking.addEvent(match);

        schedule.publish();
        match.play();

        Money sample = new Money(new BigDecimal("20.00"), Currency.USD);
        LOGGER.info(sample.toString());

        Predicate<Event> eventPolicy = Objects::nonNull;
        Predicate<Bookable> availabilityPolicy = b -> b != null && b.isAvailable();
        BiFunction<Event, Integer, java.math.BigDecimal> pricingPolicy = (e, att) -> booking.calculateTicketPrice(e.priceMultiplier());
        UnaryOperator<Integer> attendanceAdjuster = UnaryOperator.identity();
        Supplier<java.util.UUID> correlationId = java.util.UUID::randomUUID;
        Runnable before = () -> LOGGER.info("Before booking hook");
        Runnable after = () -> LOGGER.info("After booking hook");
        Consumer<String> notifier = LOGGER::info;
        Consumer<BookingLog> audit = l -> {};
        Function<Event, String> descriptor = e -> (e instanceof Match m) ? (m.getHomeTeam() + " vs " + m.getAwayTeam()) : e.getDescription();

        try {
            booking.bookWithPolicies(
                    match,
                    stadium,
                    coach,
                    18000,
                    eventPolicy,
                    availabilityPolicy,
                    pricingPolicy,
                    attendanceAdjuster,
                    correlationId,
                    before,
                    after,
                    notifier,
                    audit,
                    descriptor
            );
        } catch (OverbookingException e) {
            LOGGER.error("Could not book match: {}", e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("Booking failed: {}", e.getMessage());
        }

        stadium.cancel();

        try {
            booking.bookWithPolicies(
                    schedule,
                    stadium,
                    coach,
                    18000,
                    eventPolicy,
                    availabilityPolicy,
                    pricingPolicy,
                    attendanceAdjuster,
                    correlationId,
                    before,
                    after,
                    notifier,
                    audit,
                    descriptor
            );
        } catch (OverbookingException e) {
            LOGGER.error("Could not book schedule: {}", e.getMessage());
        } catch (RuntimeException e) {
            LOGGER.error("Booking failed: {}", e.getMessage());
        }

        coach.train();
        teams.get(0).groupTraining();

        players.get(0).scoreGoal();
        officials.get(0).officiateMatch();
        teams.get(0).playMatch();

        stadium.open();
        training.open();

        teams.get(0).printTeamInfo();
        int generatedId = IdGenerator.nextId();
        LOGGER.info("Generated id: {}", generatedId);

        LOGGER.info("\n=== System Details ===");
        LOGGER.info(teams.get(0));
        LOGGER.info(teams.get(1));
        LOGGER.info(league);
        LOGGER.info(match);
        LOGGER.info("Booked events: {}", booking.getBookedEvents().size());

        List<Player> roster = teams.get(0).getRoster();
        LOGGER.info("Roster size: {}", roster.size());
        Player firstList = roster.get(0);
        roster.stream().map(Player::fullName).forEach(n -> LOGGER.info("Roster player: {}", n));

        Set<Coach> coaches = new HashSet<>();
        coaches.add(coach);
        LOGGER.info("Coaches isEmpty? {}", coaches.isEmpty());
        Coach firstSet = coaches.iterator().next();
        LOGGER.info("First coach: {}", firstSet.fullName());
        coaches.remove(coach);

        Map<Facility, List<Event>> bookingsByFacility = new HashMap<>();
        bookingsByFacility.put(stadium, new ArrayList<>());
        bookingsByFacility.get(stadium).add(match);
        LOGGER.info("Map size: {}", bookingsByFacility.size());
        bookingsByFacility.entrySet().stream().forEach(e -> LOGGER.info("{} -> events: {}", e.getKey().getName(), e.getValue().size()));

        Result<Team> maybeTeam = Optional.ofNullable(teamRepo.get(teams.get(0).getId())).map(Result::ok).orElse(Result.error("Team not found"));
        LOGGER.info("Result ok? {}", maybeTeam.isOk());

        LOGGER.info("\n=== Reflection Demo ===");
        Class<?> playerClass = Player.class;
        for (Field f : playerClass.getDeclaredFields()) {
            LOGGER.info("{} {} {}", Modifier.toString(f.getModifiers()), f.getType().getSimpleName(), f.getName());
        }
        for (Constructor<?> c : playerClass.getDeclaredConstructors()) {
            LOGGER.info("{} {}({})", Modifier.toString(c.getModifiers()), c.getName(), Arrays.toString(c.getParameterTypes()));
        }
        for (Method m : playerClass.getDeclaredMethods()) {
            LOGGER.info("{} {} {}({})", Modifier.toString(m.getModifiers()), m.getReturnType().getSimpleName(), m.getName(), Arrays.toString(m.getParameterTypes()));
        }
        Constructor<?> ctor = playerClass.getConstructor(Integer.class, String.class, String.class, LocalDate.class, Position.class, int.class, boolean.class);
        Object playerObj = ctor.newInstance(1, "Gocha", "Kakhniashvili", LocalDate.of(1990, 5, 1), Position.FORWARD, 10, true);
        Method trainMethod = playerClass.getMethod("train");
        trainMethod.invoke(playerObj);
        if (playerClass.isAnnotationPresent(Auditable.class)) {
            Auditable ann = playerClass.getAnnotation(Auditable.class);
            LOGGER.info("Player class annotated: {}", ann.value());
        }
        for (Method m : playerClass.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Auditable.class)) {
                LOGGER.info("Method {} annotated: {}", m.getName(), m.getAnnotation(Auditable.class).value());
            }
        }
    }

    private static Coach createCoach() {
        return new Coach(1, "Giorgi", "Beridze", LocalDate.of(1980, 5, 12), 15, "UEFA Pro");
    }

    private static List<Player> createPlayers() {
        return new ArrayList<>(List.of(
                new Player(10, "Nika", "Kapanadze", LocalDate.of(1997, 3, 1), Position.FORWARD, 9, true),
                new Player(11, "Levan", "Chkheidze", LocalDate.of(2000, 7, 22), Position.MIDFIELDER, 8, false),
                new Player(12, "Irakli", "Gelashvili", LocalDate.of(1999, 1, 14), Position.DEFENDER, 4, false)
        ));
    }

    private static List<Referee> createReferees() {
        return new ArrayList<>(List.of(
                new Referee(100, "Mariam", "Ninoshvili", LocalDate.of(1985, 9, 30), "FIFA Elite", 250),
                new Referee(101, "Lasha", "Gogoladze", LocalDate.of(1986, 2, 18), "FIFA Elite", 210)
        ));
    }

    private static List<Team> createTeams(Coach coach, List<Player> players) {
        Team teamA = new Team(101, "Red Hawks", Year.of(2005), "Tbilisi");
        teamA.setHeadCoach(coach);
        teamA.setDivision(Team.Division.EAST);
        players.forEach(p -> { teamA.addPlayer(p); teamA.addMember(p); });
        teamA.addMember(coach);

        Team teamB = new Team(102, "Blue Bears", Year.of(2010), "Batumi");
        teamB.setDivision(Team.Division.WEST);
        return List.of(teamA, teamB);
    }

    private static League createLeague(List<Team> teams) {
        League league = new League(1000, "National Premier League", Year.of(1998), "Football", Year.of(2025));
        league.setSeasonStartDate(LocalDate.of(2025, 8, 15));
        teams.forEach(league::addTeam);
        return league;
    }

    private static Stadium createStadium() {
        return new Stadium(201, "National Stadium", "Tbilisi", 25000, "Grass", 1.2f, 'A');
    }

    private static TrainingGround createTrainingGround() {
        return new TrainingGround(202, "Training Center", "Batumi", true, 5);
    }

    private static Schedule createSchedule(League league) {
        Schedule schedule = new Schedule(3000, league.getName(), 1);
        schedule.setRoundDate(LocalDate.of(2025, 8, 15));
        schedule.setSpectatorsExpected(18000);
        return schedule;
    }

    private static Match createMatch(Team teamA, Team teamB, Stadium stadium, List<Referee> officials, List<Player> players) {
        Match match = new Match(4000, teamA.getName(), teamB.getName());
        match.setStadiumName(stadium.getName());
        match.setExpectedAttendance(18000);
        match.setOfficials(officials);
        match.setHomeSquad(players);
        match.setAwaySquad(Collections.emptyList());
        match.setTitle("League opener");
        return match;
    }
}
