package app;

import exception.OverbookingException;
import facility.*;
import organization.*;
import person.*;
import event.*;
import services.BookingService;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import common.Result;

public class Main {
    public static void main(String[] args) {


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

        System.out.println("Team repo size: " + teamRepo.size());
        System.out.println("Player repo empty? " + playerRepo.isEmpty());


        BookingService booking = new BookingService();
        booking.addEvent(schedule);
        booking.addEvent(match);

        schedule.publish();
        match.play();

        try {
            booking.book(match, stadium, coach, 18000);
        } catch (OverbookingException e) {
            System.err.println("Could not book match: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Booking failed: " + e.getMessage());
        }

        stadium.cancel();

        try {
            booking.book(schedule, stadium, coach, 18000);
        } catch (OverbookingException e) {
            System.err.println("Could not book schedule: " + e.getMessage());
        } catch (RuntimeException e) {
            System.err.println("Booking failed: " + e.getMessage());
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
        System.out.println("Generated id: " + generatedId);

        System.out.println("\n=== System Details ===");
        System.out.println(teams.get(0));
        System.out.println(teams.get(1));
        System.out.println(league);
        System.out.println(match);
        System.out.println("Booked events: " + booking.getBookedEvents().size());


        List<Player> roster = teams.get(0).getRoster();
        System.out.println("Roster size: " + roster.size());
        Player firstList = roster.get(0);
        for (Player p : roster) {
            System.out.println("Roster player: " + p.fullName());
        }


        Set<Coach> coaches = new HashSet<>();
        coaches.add(coach);
        System.out.println("Coaches isEmpty? " + coaches.isEmpty());
        Coach firstSet = coaches.iterator().next();
        System.out.println("First coach: " + firstSet.fullName());
        coaches.remove(coach);


        Map<Facility, List<Event>> bookingsByFacility = new HashMap<>();
        bookingsByFacility.put(stadium, new ArrayList<>());
        bookingsByFacility.get(stadium).add(match);
        System.out.println("Map size: " + bookingsByFacility.size());
        Map.Entry<Facility, List<Event>> firstMap =
                bookingsByFacility.entrySet().iterator().next();
        System.out.println("First facility key: " + firstMap.getKey().getName());
        for (Map.Entry<Facility, List<Event>> e : bookingsByFacility.entrySet()) {
            System.out.println(e.getKey().getName() + " -> events: " + e.getValue().size());
        }


        Result<Team> maybeTeam = Optional.ofNullable(teamRepo.get(teams.get(0).getId()))
                .map(Result::ok)
                .orElse(Result.error("Team not found"));
        System.out.println("Result ok? " + maybeTeam.isOk());
    }

    private static Coach createCoach() {
        return new Coach(1, "Giorgi", "Beridze", LocalDate.of(1980, 5, 12), 15, "UEFA Pro");
    }

    private static List<Player> createPlayers() {
        return new ArrayList<>(List.of(
                new Player(10, "Nika", "Kapanadze", LocalDate.of(1997, 3, 1), "Forward", 9, true),
                new Player(11, "Levan", "Chkheidze", LocalDate.of(2000, 7, 22), "Midfielder", 8, false),
                new Player(12, "Irakli", "Gelashvili", LocalDate.of(1999, 1, 14), "Defender", 4, false)
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
        players.forEach(p -> {
            teamA.addPlayer(p);
            teamA.addMember(p);
        });
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
