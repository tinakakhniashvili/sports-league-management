package event;

import contracts.Schedulable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Schedule extends Event {

    private static final int MAX_ROUNDS = 50;

    private String leagueName;
    private int roundNumber;
    private LocalDate roundDate;
    private long spectatorsExpected;

    private final List<Schedulable> items = new ArrayList<>();

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
        System.out.println(String.format("Publishing schedule for %s, round %d on %s with expected spectators: %d",
                leagueName, roundNumber, roundDate, spectatorsExpected));
    }

    public void add(Schedulable item) {
        items.add(Objects.requireNonNull(item, "item cannot be null"));
    }

    public void addAll(List<? extends Schedulable> schedulables) {
        if (schedulables == null) return;
        for (Schedulable s : schedulables) add(s);
    }

    public List<Schedulable> getItems() {
        return List.copyOf(items);
    }

    public void printAgenda() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (Schedulable s : items) {
            LocalDateTime start = s.getStartTime();
            LocalDateTime end = s.getEndTime();
            String title = s.getTitle();
            System.out.printf("%s  %s—%s%n",
                    title,
                    start != null ? start.format(fmt) : "N/A",
                    end != null ? end.format(fmt) : "N/A");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Schedule)) return false;
        if (!super.equals(o)) return false;
        Schedule schedule = (Schedule) o;
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
