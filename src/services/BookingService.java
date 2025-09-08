package services;

import event.Event;
import event.Match;
import facility.Stadium;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

public class BookingService {

    private static final BigDecimal BASE_TICKET_PRICE = new BigDecimal("20.00");
    private Event[] events;
    private int eventCount;
    private static final int INITIAL_CAPACITY = 10;

    public BookingService() {
        events = new Event[INITIAL_CAPACITY];
        eventCount = 0;
    }

    public void addEvent(Event event) {
        Objects.requireNonNull(event, "event cannot be null");
        if (eventCount >= events.length) {
            events = Arrays.copyOf(events, events.length * 2);
        }
        events[eventCount++] = event;
    }

    public BigDecimal calculateTicketPrice(BigDecimal multiplier) {
        return BASE_TICKET_PRICE.multiply(Objects.requireNonNull(multiplier, "multiplier cannot be null"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public boolean book(Event event, Stadium stadium, int expectedAttendance) {
        if (event == null || stadium == null) {
            System.out.println("Booking failed: Invalid event or stadium");
            return false;
        }
        if (expectedAttendance <= 0 || expectedAttendance > stadium.getCapacity()) {
            System.out.println("Booking failed: Invalid attendance");
            return false;
        }
        if (event instanceof Match match) {
            if (match.getExpectedAttendance() > 0 && match.getExpectedAttendance() != expectedAttendance) {
                System.out.println("Booking failed: Attendance mismatch");
                return false;
            }
            match.setExpectedAttendance(expectedAttendance);
        }
        BigDecimal ticketPrice = calculateTicketPrice(event.priceMultiplier());
        BigDecimal revenue = ticketPrice.multiply(BigDecimal.valueOf(expectedAttendance));
        String eventDescription = event instanceof Match match
                ? String.format("%s vs %s", match.getHomeTeam(), match.getAwayTeam())
                : event.getDescription();
        System.out.println(String.format("Booking confirmed for %s at %s", eventDescription, stadium.getName()));
        System.out.println(String.format("Expected attendance: %d", expectedAttendance));
        System.out.println(String.format("Ticket price: $%.2f", ticketPrice));
        System.out.println(String.format("Projected revenue: $%.2f", revenue));
        System.out.println("Email sent to organizer");
        addEvent(event);
        return true;
    }

    public Event[] getBookedEvents() {
        return Arrays.copyOf(events, eventCount);
    }
}