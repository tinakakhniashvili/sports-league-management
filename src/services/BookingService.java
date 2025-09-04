package services;

import events.Match;
import facilities.Stadium;

public class BookingService {

    private static final double BASE_TICKET_PRICE = 20.0;

    public static double calculateTicketPrice(double multiplier) {
        return BASE_TICKET_PRICE * multiplier;
    }

    public boolean book(Match match, Stadium stadium, int expectedAttendance, double priceMultiplier) {
        if (stadium == null || match == null) {
            System.out.println("Booking failed");
            return false;
        }
        if (expectedAttendance <= 0 || expectedAttendance > stadium.getCapacity()) {
            System.out.println("Booking failed");
            return false;
        }
        double ticketPrice = calculateTicketPrice(priceMultiplier);
        double revenue = expectedAttendance * ticketPrice;
        System.out.println("Booking confirmed for " + match.getHomeTeam() + " vs " + match.getAwayTeam() + " at " + stadium.getName());
        System.out.println("Expected attendance: " + expectedAttendance);
        System.out.println("Ticket price: " + ticketPrice);
        System.out.println("Projected revenue: " + revenue);
        System.out.println("Email sent to organizer");
        return true;
    }
}
