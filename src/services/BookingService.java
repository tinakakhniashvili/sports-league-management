package services;

import contracts.Bookable;
import event.Event;
import event.Match;
import exception.FacilityUnavailableException;
import exception.OverbookingException;
import facility.Stadium;
import person.Person;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

public class BookingService {

    private static final BigDecimal BASE_TICKET_PRICE = new BigDecimal("20.00");
    private static final int INITIAL_CAPACITY = 10;

    private Event[] events;
    private int eventCount;

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

    public void book(Event event, Bookable facility, Person organizer, int expectedAttendance) throws OverbookingException {
        if (event == null || facility == null || organizer == null) {
            throw new IllegalArgumentException("Invalid event, facility, or organizer");
        }
        if (!facility.isAvailable()) {
            throw new FacilityUnavailableException("Facility is not available");
        }
        if (expectedAttendance <= 0) {
            throw new OverbookingException("Expected attendance must be > 0");
        }
        if (facility instanceof Stadium s) {
            if (expectedAttendance > s.getCapacity()) {
                throw new OverbookingException("Expected " + expectedAttendance + " exceeds capacity " + s.getCapacity());
            }
        }
        if (event instanceof Match match) {
            long existing = match.getExpectedAttendance();
            if (existing > 0 && existing != expectedAttendance) {
                throw new OverbookingException("Attendance mismatch for match");
            }
            match.setExpectedAttendance(expectedAttendance);
        }
        BigDecimal ticketPrice = calculateTicketPrice(event.priceMultiplier());
        BigDecimal revenue = ticketPrice.multiply(BigDecimal.valueOf(expectedAttendance));
        String facilityName = (facility instanceof Stadium s) ? s.getName() : facility.getClass().getSimpleName();
        String eventDescription = (event instanceof Match match)
                ? String.format("%s vs %s", match.getHomeTeam(), match.getAwayTeam())
                : event.getDescription();
        try (BookingLog log = new BookingLog("bookings.log")) {
            facility.book(organizer);
            addEvent(event);
            log.info("Booked " + eventDescription + " at " + facilityName + " | expected=" + expectedAttendance + " | ticket=" + ticketPrice + " | revenue=" + revenue);
            System.out.println(String.format("Booking confirmed for %s at %s", eventDescription, facilityName));
            System.out.println(String.format("Organizer: %s", organizer.fullName()));
            System.out.println(String.format("Expected attendance: %d", expectedAttendance));
            System.out.println(String.format("Ticket price: $%.2f", ticketPrice));
            System.out.println(String.format("Projected revenue: $%.2f", revenue));
            System.out.println("Email sent to organizer");
        } catch (java.io.IOException ioe) {
            throw new RuntimeException("Failed to write booking log.", ioe);
        }
    }

    public Event[] getBookedEvents() {
        return Arrays.copyOf(events, eventCount);
    }

    private boolean validateAttendanceFor(Bookable facility, int expectedAttendance) {
        if (expectedAttendance <= 0) return false;
        if (facility instanceof Stadium s) {
            return expectedAttendance <= s.getCapacity();
        }
        return true;
    }
}
