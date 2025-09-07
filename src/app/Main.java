package app;

import facility.*;
import organization.*;
import person.*;
import event.*;
import services.BookingService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

public class Main {
    public static void main(String[] args) {

        Coach coach = createCoach();
        Player[] players = createPlayers();
        Referee[] officials = createReferees();
        Team[] teams = createTeams(coach, players);
        League league = createLeague(teams);
        Stadium stadium = createStadium();
        TrainingGround training = createTrainingGround();
        Schedule schedule = createSchedule(league);
        Match match = createMatch(teams[0], teams[1], stadium, officials, players);

        BookingService booking = new BookingService();
        booking.addEvent(schedule);
        booking.addEvent(match);

        schedule.publish();
        match.startEvent();
        match.play();
        booking.book(match, stadium, 18000);
        booking.book(schedule, stadium, 18000);
        coach.trainTeam();
        players[0].scoreGoal();
        officials[0].officiateMatch();
        teams[0].playMatch();
        stadium.hostMatch();
        training.conductTraining();
        league.scheduleSeason();

        System.out.println("\n=== System Details ===");
        System.out.println(teams[0]);
        System.out.println(teams[1]);
        System.out.println(league);
        System.out.println(match);
        System.out.println("Booked events: " + booking.getBookedEvents());
    }

    private static Coach createCoach() {
        return new Coach(1, "Giorgi", "Beridze", LocalDate.of(1980, 5, 12), 15, "UEFA Pro");
    }

    private static Player[] createPlayers() {
        return new Player[] {
                new Player(10, "Nika", "Kapanadze", LocalDate.of(1997, 3, 1), "Forward", 9, true),
                new Player(11, "Levan", "Chkheidze", LocalDate.of(2000, 7, 22), "Midfielder", 8, false),
                new Player(12, "Irakli", "Gelashvili", LocalDate.of(1999, 1, 14), "Defender", 4, false)
        };
    }

    private static Referee[] createReferees() {
        return new Referee[] {
                new Referee(100, "Mariam", "Ninoshvili", LocalDate.of(1985, 9, 30), "FIFA Elite", 250),
                new Referee(101, "Lasha", "Gogoladze", LocalDate.of(1986, 2, 18), "FIFA Elite", 210)
        };
    }

    private static Team[] createTeams(Coach coach, Player[] players) {
        Team teamA = new Team(101, "Red Hawks", Year.of(2005), "Tbilisi");
        teamA.setHeadCoach(coach);
        teamA.setDivision(Team.Division.EAST);
        for (Player p : players) {
            teamA.addPlayer(p);
        }
        Team teamB = new Team(102, "Blue Bears", Year.of(2010), "Batumi");
        teamB.setDivision(Team.Division.WEST);
        return new Team[] { teamA, teamB };
    }

    private static League createLeague(Team[] teams) {
        League league = new League(1000, "National Premier League", Year.of(1998), "Football", Year.of(2025));
        league.setSeasonStartDate(LocalDate.of(2025, 8, 15));
        league.setTeams(teams);
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

    private static Match createMatch(Team teamA, Team teamB, Stadium stadium, Referee[] officials, Player[] players) {
        Match match = new Match(4000, teamA.getName(), teamB.getName());
        match.setStadiumName(stadium.getName());
        match.setExpectedAttendance(18000);
        match.setOfficials(officials);
        match.setHomeSquad(players);
        match.setAwaySquad(new Player[0]);
        match.reschedule(LocalDateTime.of(2025, 8, 15, 19, 30));
        match.updateDescription("League opener");
        return match;
    }
}