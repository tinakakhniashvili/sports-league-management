package com.solvd.sportsleaguemanagement.event;

import com.solvd.sportsleaguemanagement.contracts.Schedulable;
import com.solvd.sportsleaguemanagement.exception.ScheduleConflictException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Schedule extends Event {

    private static final int MAX_ROUNDS = 50;
    private final List<Schedulable> items = new ArrayList<>();

    private String leagueName;
    private int roundNumber;
    private LocalDate roundDate;
    private long spectatorsExpected;

    static {
        System.out.println("Schedule class loaded");
    }

    public Schedule(Integer id, String leagueName, int roundNumber) {
        super(id);
        this.leagueName = Objects.requireNonNull(leagueName, "leagueName cannot be null");
        if (!isValidRound(roundNumber)) throw new IllegalArgumentException("Invalid round number");
        this.roundNumber = roundNumber;
    }

    @Override
    public BigDecimal priceMultiplier() {
        return new BigDecimal("0.50");
    }

    public static boolean isValidRound(int round) {
        return round > 0 && round <= MAX_ROUNDS;
    }

    public void publish() {
        System.out.printf("Publishing schedule for %s, round %d on %s with expected spectators: %d%n",
                leagueName, roundNumber, roundDate, spectatorsExpected);
    }

    public void add(Schedulable item) {
        Objects.requireNonNull(item, "item cannot be null");
        boolean conflict = items.stream()
                .anyMatch(ex -> overlaps(ex, item));
        if (conflict) {
            throw new ScheduleConflictException(
                    "Schedule conflict: " + items.stream()
                            .filter(ex -> overlaps(ex, item))
                            .findFirst()
                            .map(Schedulable::getTitle)
                            .orElse("Existing")
                            + "'overlaps'" + item.getTitle() + "'"
            );
        }
        items.add(item);
    }

    public void addAll(List<? extends Schedulable> schedulables) {
        if (schedulables == null) return;
        schedulables.forEach(this::add);
    }

    public List<Schedulable> getItems() {
        return List.copyOf(items);
    }

    public void printAgenda() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        items.stream()
                .map(s -> {
                    LocalDateTime start = s.getStartTime();
                    LocalDateTime end = s.getEndTime();
                    String startStr = (start != null) ? start.format(fmt) : "N/A";
                    String endStr = (end != null) ? end.format(fmt) : "N/A";
                    return String.format("%s: %s (%s)", s.getTitle(), startStr, endStr);
                })
                .forEach(System.out::println);
    }

    private boolean overlaps(Schedulable a, Schedulable b) {
        LocalDateTime aStart = a.getStartTime(), aEnd = a.getEndTime();
        LocalDateTime bStart = b.getStartTime(), bEnd = b.getEndTime();
        if (aStart == null || aEnd == null || bStart == null || bEnd == null) return false;
        return aStart.isBefore(bEnd) && bStart.isBefore(aEnd);
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = Objects.requireNonNull(leagueName, "leagueName cannot be null");
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
        if (!isValidRound(roundNumber)) throw new IllegalArgumentException("Invalid round number");
        this.roundNumber = roundNumber;
    }

    public LocalDate getRoundDate() {
        return roundDate;
    }

    public void setRoundDate(LocalDate roundDate) {
        this.roundDate = roundDate;
    }

    public long getSpectatorsExpected() {
        return spectatorsExpected;
    }

    public void setSpectatorsExpected(long spectatorsExpected) {
        if (spectatorsExpected >= 0) this.spectatorsExpected = spectatorsExpected;
    }

    public List<String> getAgendaTitles() {
        return items.stream()
                .map(Schedulable::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule schedule)) return false;
        if (!super.equals(o)) return false;
        return roundNumber == schedule.roundNumber && Objects.equals(leagueName, schedule.leagueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), leagueName, roundNumber);
    }

    @Override
    public String toString() {
        return String.format("%s, leagueName='%s', roundNumber=%d, roundDate=%s, spectatorsExpected=%d",
                super.toString(), leagueName, roundNumber, roundDate, spectatorsExpected);
    }
}
