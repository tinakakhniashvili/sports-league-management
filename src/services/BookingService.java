package services;

import common.functional.EventHandler;
import common.functional.TriFunction;
import contracts.Bookable;
import event.Event;
import event.Match;
import exception.FacilityUnavailableException;
import exception.OverbookingException;
import facility.Stadium;
import person.Person;
import person.Player;
import person.Referee;
import types.Severity;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.*;
import java.util.stream.Collectors;

public class BookingService {

    private static final BigDecimal BASE_TICKET_PRICE = new BigDecimal("20.00");

    private final ReentrantLock lock = new ReentrantLock();
    private final List<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        Objects.requireNonNull(event, "event cannot be null");
        events.add(event);
    }

    public BigDecimal calculateTicketPrice(BigDecimal multiplier) {
        return BASE_TICKET_PRICE
                .multiply(Objects.requireNonNull(multiplier, "multiplier cannot be null"))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void book(Event event, Bookable facility, Person organizer, int expectedAttendance)
            throws OverbookingException {
        Predicate<Event> eventPolicy = Objects::nonNull;
        Predicate<Bookable> availabilityPolicy = b -> b != null && b.isAvailable();
        TriFunction<Event, Integer, BigDecimal, BigDecimal> pricingPolicy3 = (e, att, base) -> calculateTicketPrice(e.priceMultiplier());
        BiFunction<Event, Integer, BigDecimal> pricingPolicy = (e, att) -> pricingPolicy3.apply(e, att, BASE_TICKET_PRICE);
        UnaryOperator<Integer> attendanceAdjuster = UnaryOperator.identity();
        Supplier<UUID> correlationId = UUID::randomUUID;
        Runnable before = () -> {
        };
        Runnable after = () -> {
        };
        Consumer<String> notifier = System.out::println;
        Consumer<BookingLog> audit = l -> {
        };
        Function<Event, String> descriptor = e -> {
            if (e instanceof Match m) return m.getHomeTeam() + " vs " + m.getAwayTeam();
            return e.getDescription();
        };
        bookWithPolicies(event, facility, organizer, expectedAttendance,
                eventPolicy, availabilityPolicy, pricingPolicy, attendanceAdjuster,
                correlationId, before, after, notifier, audit, descriptor);
    }

    public void bookWithPolicies(
            Event event,
            Bookable facility,
            Person organizer,
            int expectedAttendance,
            Predicate<Event> eventPolicy,
            Predicate<Bookable> availabilityPolicy,
            BiFunction<Event, Integer, BigDecimal> pricingPolicy,
            UnaryOperator<Integer> attendanceAdjuster,
            Supplier<UUID> correlationId,
            Runnable beforeHook,
            Runnable afterHook,
            Consumer<String> notifier,
            Consumer<BookingLog> audit,
            Function<Event, String> eventDescriptor
    ) throws OverbookingException {

        if (!eventPolicy.test(event) || organizer == null) throw new IllegalArgumentException("Invalid input");
        if (!availabilityPolicy.test(facility)) throw new FacilityUnavailableException("Facility is not available");

        int adjustedAttendance = attendanceAdjuster.apply(expectedAttendance);
        if (adjustedAttendance <= 0) throw new OverbookingException("Expected attendance must be > 0");

        if (facility instanceof Stadium s && adjustedAttendance > s.getCapacity())
            throw new OverbookingException("Expected " + adjustedAttendance + " exceeds capacity " + s.getCapacity());

        if (event instanceof Match match) {
            long existing = match.getExpectedAttendance();
            if (existing > 0 && existing != adjustedAttendance)
                throw new OverbookingException("Attendance mismatch for match");
            match.setExpectedAttendance(adjustedAttendance);
        }

        BigDecimal ticketPrice = pricingPolicy.apply(event, adjustedAttendance);
        BigDecimal revenue = ticketPrice.multiply(BigDecimal.valueOf(adjustedAttendance));
        String facilityName = (facility instanceof Stadium s) ? s.getName() : facility.getClass().getSimpleName();
        String eventDescription = eventDescriptor.apply(event);
        UUID corr = correlationId.get();

        EventHandler<String> handler = notifier::accept;

        lock.lock();
        try {
            beforeHook.run();
            try (BookingLog log = new BookingLog("bookings.log")) {
                facility.book(organizer);
                addEvent(event);
                log.log(Severity.LOW, "corr=" + corr + " | Booked " + eventDescription + " at " + facilityName
                        + " | expected=" + adjustedAttendance
                        + " | ticket=" + ticketPrice
                        + " | revenue=" + revenue);
                audit.accept(log);
                notifier.accept("Booking confirmed for " + eventDescription + " at " + facilityName);
                notifier.accept("Organizer: " + organizer.fullName());
                notifier.accept("Expected attendance: " + adjustedAttendance);
                notifier.accept("Ticket price: $" + ticketPrice);
                notifier.accept("Projected revenue: $" + revenue);
                handler.handle("Booking event logged: " + eventDescription);
            } catch (IOException ioe) {
                throw new RuntimeException("Failed to write booking log.", ioe);
            } finally {
                afterHook.run();
            }
        } finally {
            lock.unlock();
        }
    }

    public List<Event> getBookedEvents() {
        return List.copyOf(events);
    }

    private boolean validateAttendanceFor(Bookable facility, int expectedAttendance) {
        if (expectedAttendance <= 0) return false;
        if (facility instanceof Stadium s) {
            return expectedAttendance <= s.getCapacity();
        }
        return true;
    }

    public List<Player> getAllPlayersFromBookedMatches() {
        return events.stream()
                .filter(Match.class::isInstance)
                .map(Match.class::cast)
                .flatMap(m -> {
                    List<Player> all = new ArrayList<>();
                    all.addAll(m.getHomeSquad());
                    all.addAll(m.getAwaySquad());
                    return all.stream();
                })
                .collect(Collectors.toList());
    }

    public List<Referee> getAllRefereesFromBookedMatches() {
        return events.stream()
                .filter(Match.class::isInstance)
                .map(Match.class::cast)
                .flatMap(m -> m.getOfficials().stream())
                .collect(Collectors.toList());
    }
}
