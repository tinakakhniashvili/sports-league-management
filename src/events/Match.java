package events;

public class Match extends Event {
    private String homeTeam;
    private String awayTeam;
    private String stadiumName;
    private String refereeName;
    private int homeScore;
    private int awayScore;

    private static final int MAX_SCORE = 99;

    static {
        System.out.println("Match class loaded");
    }

    public Match(int id, String dateTime, String description, String homeTeam, String awayTeam, String stadiumName, String refereeName) {
        super(id, dateTime, description);
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadiumName = stadiumName;
        this.refereeName = refereeName;
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= MAX_SCORE;
    }

    public void play() {
        System.out.println("Match: " + homeTeam + " vs " + awayTeam + " at " + stadiumName + " officiated by " + refereeName + ".");
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

    public String getRefereeName() {
        return refereeName;
    }

    public void setRefereeName(String refereeName) {
        this.refereeName = refereeName;
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

    @Override
    public String toString() {
        return super.toString() +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", refereeName='" + refereeName + '\'' +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore;
    }
}
