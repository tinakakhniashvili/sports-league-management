package services;

import event.Match;
import facility.Stadium;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BookingService {

    private static final BigDecimal BASE_TICKET_PRICE = new BigDecimal("20.00");

    public static BigDecimal calculateTicketPrice(BigDecimal multiplier) {
        return BASE_TICKET_PRICE.multiply(multiplier).setScale(2, RoundingMode.HALF_UP);
    }

    public boolean book(Match match, Stadium stadium, int expectedAttendance, BigDecimal priceMultiplier) {
        if (stadium == null || match == null) {
            System.out.println("Booking failed");
            return false;
        }
        if (expectedAttendance <= 0 || expectedAttendance > stadium.getCapacity()) {
            System.out.println("Booking failed");
            return false;
        }
        BigDecimal ticketPrice = calculateTicketPrice(priceMultiplier);
        BigDecimal revenue = ticketPrice.multiply(BigDecimal.valueOf(expectedAttendance));
        System.out.println("Booking confirmed for " + match.getHomeTeam() + " vs " + match.getAwayTeam() + " at " + stadium.getName());
        System.out.println("Expected attendance: " + expectedAttendance);
        System.out.println("Ticket price: " + ticketPrice);
        System.out.println("Projected revenue: " + revenue);
        System.out.println("Email sent to organizer");
        return true;
    }
}
