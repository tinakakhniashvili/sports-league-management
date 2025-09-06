package event;

import person.Player;
import person.Referee;

public class Match extends Event {

    private static final int MAX_SCORE = 99;

    private final String homeTeam;
    private final String awayTeam;
    private String stadiumName;
    private int homeScore;
    private int awayScore;
    private long expectedAttendance;
    private Referee[] officials =  new Referee[0];
    private Player[] homeSquad = new Player[0];
    private Player[] awaySquad = new Player[0];

    static {
        System.out.println("Match class loaded");
    }

    public Match(Integer id, String homeTeam, String awayTeam) {
        super(id);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
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

    public String getAwayTeam() {
        return awayTeam;
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
        if(isValidScore(homeScore)) this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        if(isValidScore(awayScore)) this.awayScore = awayScore;
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
