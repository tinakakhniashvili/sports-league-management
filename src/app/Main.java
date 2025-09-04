package app;

import people.*;
import organizations.*;
import facilities.*;
import events.*;
import services.BookingService;

public class Main {

    public static void main(String[] args) {

        Player player = new Player(1, "Giorgi", "Mamulashvili", 23, "Forward", 10, true);
        Coach coach = new Coach(2, "Avtandil", "Kvachantiradze", 50, 25, "Pro License");
        Referee referee = new Referee(3, "Zaza", "Kakabadze", 40, "FIFA", 200);

        Team team = new Team(101, "Tigers", 1990, "New York", coach.fullName(), 22);
        League league = new League(201, "Premier League", 1992, "Football", 2025, 20);

        Stadium stadium = new Stadium(301, "National Stadium", "New York", 60000, "Grass");
        TrainingGround trainingGround = new TrainingGround(302, "Training Center", "Brooklyn", true, 5);

        Match match = new Match(401, "2025-09-05 18:00", "Season opener", "Tigers", "Lions", stadium.getName(), referee.fullName());
        Schedule schedule = new Schedule(501, "2025-09-01", "League schedule", league.getName(), 1);

        player.scoreGoal();
        coach.trainTeam();
        referee.officiateMatch();
        team.playMatch();
        league.scheduleSeason();
        stadium.hostMatch();
        trainingGround.conductTraining();
        match.play();
        schedule.publish();

        BookingService bookingService = new BookingService();
        bookingService.book(match, stadium, 18000, 1.25);

        System.out.println(player);
        System.out.println(coach);
        System.out.println(referee);
        System.out.println(team);
        System.out.println(league);
        System.out.println(stadium);
        System.out.println(trainingGround);
        System.out.println(match);
        System.out.println(schedule);
    }
}
