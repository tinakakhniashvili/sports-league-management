package events;

public class Schedule extends Event {
    private String leagueName;
    private int roundNumber;

    private static final int MAX_ROUNDS = 50;

    static {
        System.out.println("Schedule class loaded");
    }

    public Schedule(int id, String dateTime, String description, String leagueName, int roundNumber) {
        super(id, dateTime, description);
        this.leagueName = leagueName;
        this.roundNumber = roundNumber;
    }

    public static boolean isValidRound(int round) {
        return round > 0 && round <= MAX_ROUNDS;
    }

    public void publish() {
        System.out.println("Publishing schedule for " + leagueName + ", round " + roundNumber + ".");
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

    @Override
    public String toString() {
        return super.toString() +
                ", leagueName='" + leagueName + '\'' +
                ", roundNumber=" + roundNumber;
    }
}
