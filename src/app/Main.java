package app;

import people.*;
import organizations.*;
import facilities.*;
import events.*;
import services.BookingService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        Person basePerson = new Person(9000, "Base", "Person", 30);

        Coach homeCoach = new Coach(100, "Archil", "Tsiklauri", 45, 20, "UEFA Pro");
        Coach awayCoach = new Coach(101, "Mark", "Howard", 48, 22, "UEFA Pro");

        Player[] homeRoster = new Player[] {
                new Player(1, "Alex", "Beridze", 24, "GK", 1, true),
                new Player(2, "Beka", "Kiknavelidze", 22, "DF", 4, false),
                new Player(3, "Davit", "Koberidze", 25, "DF", 5, false),
                new Player(4, "Giorgi", "Lomidze", 23, "MF", 6, false),
                new Player(5, "Nika", "Chkheidze", 27, "MF", 8, false),
                new Player(6, "Sandro", "Gogoladze", 21, "MF", 10, false),
                new Player(7, "Luka", "Abesadze", 26, "FW", 9, false),
                new Player(8, "Mate", "Khutsishvili", 20, "FW", 11, false),
                new Player(9, "Irakli", "Mchedlidze", 24, "DF", 2, false),
                new Player(10, "Levan", "Japaridze", 28, "DF", 3, false),
                new Player(11, "Zaza", "Tsiklauri", 29, "MF", 7, false)
        };

        Player[] awayRoster = new Player[] {
                new Player(12, "John", "Smith", 25, "GK", 1, true),
                new Player(13, "Leo", "Morris", 23, "DF", 4, false),
                new Player(14, "Chris", "Dunn", 22, "DF", 5, false),
                new Player(15, "Nick", "Wells", 24, "MF", 6, false),
                new Player(16, "Owen", "Baker", 27, "MF", 8, false),
                new Player(17, "Paul", "King", 21, "MF", 10, false),
                new Player(18, "Rick", "Cole", 26, "FW", 9, false),
                new Player(19, "Sam", "Young", 20, "FW", 11, false),
                new Player(20, "Tim", "Green", 24, "DF", 2, false),
                new Player(21, "Vlad", "Knight", 28, "DF", 3, false),
                new Player(22, "Yan", "Hardy", 29, "MF", 7, false)
        };

        Referee[] officials = new Referee[] {
                new Referee(200, "Ref", "One", 36, "Elite", 150),
                new Referee(201, "Ref", "Two", 34, "Elite", 130),
                new Referee(202, "Ref", "Three", 33, "Elite", 120),
                new Referee(203, "Ref", "Fourth", 32, "Elite", 110)
        };

        Stadium dinamo = new Stadium(300, "Dinamo Arena", "Tbilisi", 55000, "Hybrid Grass", 1.0f, 'A');
        TrainingGround tg1 = new TrainingGround(301, "Base A", "Tbilisi", false, 4);

        Team homeTeam = new Team(400, "FC Tbilisi", 1950, "Tbilisi", homeCoach, homeRoster, (byte) 2, 'E');
        Team awayTeam = new Team(401, "FC Riverside", 1965, "Riverside", awayCoach, awayRoster, (byte) 1, 'W');
        Team[] teams = new Team[] { homeTeam, awayTeam };

        League league = new League(500, "National League", 1936, "Football", (short) 2025, teams.length,
                LocalDate.of(2025, 8, 1), new BigDecimal("1000000.00"), teams);

        Event opening = new Event(600, LocalDateTime.of(2025, 8, 1, 18, 0), "Opening Ceremony");
        Schedule round1 = new Schedule(601, LocalDateTime.of(2025, 8, 3, 10, 0), "Round 1 Schedule",
                league.getName(), 1, LocalDate.of(2025, 8, 3), 45000L);
        Match match = new Match(700, LocalDateTime.of(2025, 8, 3, 20, 0), "Round 1 Match",
                homeTeam.getName(), awayTeam.getName(), dinamo.getName(),
                48000L, officials, homeRoster, awayRoster);

        BookingService bookingService = new BookingService();
        bookingService.book(match, dinamo, 48000, new BigDecimal("1.5"));

        opening.startEvent();
        round1.publish();
        match.play();
        dinamo.hostMatch();
        tg1.conductTraining();
        homeCoach.trainTeam();
        homeRoster[0].scoreGoal();
        officials[0].officiateMatch();
        league.scheduleSeason();
        homeTeam.playMatch();
    }
}
