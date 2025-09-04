package events;

import java.time.LocalDateTime;
import people.Player;
import people.Referee;

public class Match extends Event {

    private static final int MAX_SCORE = 99;

    private String homeTeam;
    private String awayTeam;
    private String stadiumName;
    private int homeScore;
    private int awayScore;
    private long expectedAttendance;
    private Referee[] officials;
    private Player[] homeSquad;
    private Player[] awaySquad;

    static {
        System.out.println("Match class loaded");
    }

    public Match(int id, LocalDateTime dateTime, String description,
                 String homeTeam, String awayTeam, String stadiumName,
                 long expectedAttendance, Referee[] officials,
                 Player[] homeSquad, Player[] awaySquad) {
        super(id, dateTime, description);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadiumName = stadiumName;
        this.expectedAttendance = expectedAttendance;
        this.officials = officials;
        this.homeSquad = homeSquad;
        this.awaySquad = awaySquad;
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= MAX_SCORE;
    }

    public void play() {
        System.out.println("Match: " + homeTeam + " vs " + awayTeam + " at " + stadiumName + ".");
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public long getExpectedAttendance() {
        return expectedAttendance;
    }

    public void setExpectedAttendance(long expectedAttendance) {
        this.expectedAttendance = expectedAttendance;
    }

    public Referee[] getOfficials() {
        return officials;
    }

    public void setOfficials(Referee[] officials) {
        this.officials = officials;
    }

    public Player[] getHomeSquad() {
        return homeSquad;
    }

    public void setHomeSquad(Player[] homeSquad) {
        this.homeSquad = homeSquad;
    }

    public Player[] getAwaySquad() {
        return awaySquad;
    }

    public void setAwaySquad(Player[] awaySquad) {
        this.awaySquad = awaySquad;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", expectedAttendance=" + expectedAttendance;
    }
}
