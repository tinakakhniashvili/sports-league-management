package app;

import facility.Facility;
import facility.Stadium;
import facility.TrainingGround;
import organization.League;
import organization.Team;
import organization.Team.Division;
import person.Coach;
import person.Player;
import person.Referee;
import event.Match;
import event.Schedule;
import services.BookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        Coach coach = new Coach(1, "Giorgi", "Beridze", LocalDate.of(1980, 5, 12), 15, "UEFA Pro");
        Player p1 = new Player(10, "Nika", "Kapanadze", LocalDate.of(1997, 3, 1), "Forward", 9, true);
        Player p2 = new Player(11, "Levan", "Chkheidze", LocalDate.of(2000, 7, 22), "Midfielder", 8, false);
        Player p3 = new Player(12, "Irakli", "Gelashvili", LocalDate.of(1999, 1, 14), "Defender", 4, false);
        Referee ref1 = new Referee(100, "Mariam", "Ninoshvili", LocalDate.of(1985, 9, 30), "FIFA Elite", 250);
        Referee ref2 = new Referee(101, "Lasha", "Gogoladze", LocalDate.of(1986, 2, 18), "FIFA Elite", 210);
        Referee[] officials = new Referee[] { ref1, ref2 };

        Team teamA = new Team(101, "Red Hawks", Year.of(2005), "Tbilisi");
        teamA.setHeadCoach(coach);
        teamA.setDivision(Division.EAST);
        teamA.addPlayer(p1);
        teamA.addPlayer(p2);
        teamA.addPlayer(p3);

        Team teamB = new Team(102, "Blue Bears", Year.of(2010), "Batumi");
        teamB.setDivision(Division.WEST);

        Team[] leagueTeams = new Team[] { teamA, teamB };

        League league = new League(1000, "National Premier League", Year.of(1998), "Football", Year.of(2025));
        league.setSeasonStartDate(LocalDate.of(2025, 8, 15));
        league.setTeams(leagueTeams);

        Facility baseFacility = new Facility(200, "Sports Complex", "Rustavi");
        Stadium stadium = new Stadium(201, "National Stadium", "Tbilisi", 25000, "Grass", 1.2f, 'A');
        TrainingGround training = new TrainingGround(202, "Training Center", "Batumi", true, 5);

        Schedule sched = new Schedule(3000, league.getName(), 1);
        sched.setRoundDate(LocalDate.of(2025, 8, 15));
        sched.setSpectatorsExpected(18000);
        sched.publish();

        Match match = new Match(4000, teamA.getName(), teamB.getName());
        match.setStadiumName(stadium.getName());
        match.setExpectedAttendance(18000);
        match.setOfficials(officials);
        match.setHomeSquad(new Player[] { p1, p2, p3 });
        match.setAwaySquad(new Player[] {});

        match.reschedule(LocalDateTime.of(2025, 8, 15, 19, 30));
        match.updateDescription("League opener");
        match.startEvent();
        match.play();

        BookingService booking = new BookingService();
        booking.book(match, stadium, 18000, new BigDecimal("1.25"));

        coach.trainTeam();
        p1.scoreGoal();
        ref1.officiateMatch();
        teamA.playMatch();
        stadium.hostMatch();
        training.conductTraining();
        baseFacility.open();
        league.scheduleSeason();

        System.out.println(teamA);
        System.out.println(teamB);
        System.out.println(league);
        System.out.println(match);
    }
}
