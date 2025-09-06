package event;

import java.time.LocalDate;

public class Schedule extends Event {

    private static final int MAX_ROUNDS = 50;

    private String leagueName;
    private int roundNumber;
    private LocalDate roundDate;
    private long spectatorsExpected;

    static {
        System.out.println("Schedule class loaded");
    }

    public Schedule(Integer id, String leagueName, int roundNumber) {
        super(id);
        this.leagueName = leagueName;
        this.roundNumber = roundNumber;
    }

    public static boolean isValidRound(int round) {
        return round > 0 && round <= MAX_ROUNDS;
    }

    public void publish() {
        System.out.println("Publishing schedule for " + leagueName +
                ", round " + roundNumber +
                " on " + roundDate +
                " with expected spectators: " + spectatorsExpected);
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public void setRoundNumber(int roundNumber) {
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
        this.spectatorsExpected = spectatorsExpected;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", leagueName='" + leagueName + '\'' +
                ", roundNumber=" + roundNumber +
                ", roundDate=" + roundDate +
                ", spectatorsExpected=" + spectatorsExpected;
    }
}
