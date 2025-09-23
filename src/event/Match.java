package event;

import person.Player;
import person.Referee;
import java.math.BigDecimal;
import java.util.*;
import types.MatchPhase;

public class Match extends Event {

    private static final int MAX_SCORE = 99;

    private final String homeTeam;
    private final String awayTeam;
    private String stadiumName;
    private int homeScore;
    private int awayScore;
    private long expectedAttendance;

    private final List<Referee> officials = new ArrayList<>();
    private final List<Player> homeSquad = new ArrayList<>();
    private final List<Player> awaySquad = new ArrayList<>();

    private MatchPhase phase = MatchPhase.PREPARATION;

    static {
        System.out.println("Match class loaded");
    }

    public Match(Integer id, String homeTeam, String awayTeam) {
        super(id);
        this.homeTeam = Objects.requireNonNull(homeTeam, "homeTeam cannot be null");
        this.awayTeam = Objects.requireNonNull(awayTeam, "awayTeam cannot be null");
    }

    @Override
    public BigDecimal priceMultiplier() {
        return BigDecimal.valueOf(getExpectedAttendance() >= 30000 ? 1.50 : 1.00);
    }

    public static boolean isValidScore(int score) {
        return score >= 0 && score <= MAX_SCORE;
    }

    public void play() {
        System.out.println(String.format("Match: %s vs %s at %s.", homeTeam, awayTeam, stadiumName));
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
        this.stadiumName = stadiumName != null ? stadiumName.trim() : null;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        if (isValidScore(homeScore)) this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        if (isValidScore(awayScore)) this.awayScore = awayScore;
    }

    public long getExpectedAttendance() {
        return expectedAttendance;
    }

    public void setExpectedAttendance(long expectedAttendance) {
        if (expectedAttendance >= 0) this.expectedAttendance = expectedAttendance;
    }

    public List<Referee> getOfficials() {
        return List.copyOf(officials);
    }

    public void setOfficials(Collection<Referee> refs) {
        officials.clear();
        if (refs != null) officials.addAll(refs);
    }

    public List<Player> getHomeSquad() {
        return List.copyOf(homeSquad);
    }

    public void setHomeSquad(Collection<Player> squad) {
        homeSquad.clear();
        if (squad != null) homeSquad.addAll(squad);
    }

    public List<Player> getAwaySquad() {
        return List.copyOf(awaySquad);
    }

    public void setAwaySquad(Collection<Player> squad) {
        awaySquad.clear();
        if (squad != null) awaySquad.addAll(squad);
    }

    public MatchPhase getPhase() {
        return phase;
    }

    public void setPhase(MatchPhase phase) {
        this.phase = Objects.requireNonNull(phase, "phase cannot be null");
    }

    public void advancePhase() {
        this.phase = this.phase.next();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        if (!super.equals(o)) return false;
        Match match = (Match) o;
        return Objects.equals(homeTeam, match.homeTeam) && Objects.equals(awayTeam, match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), homeTeam, awayTeam);
    }

    @Override
    public String toString() {
        return String.format("%s, homeTeam='%s', awayTeam='%s', stadiumName='%s', homeScore=%d, awayScore=%d, expectedAttendance=%d",
                super.toString(), homeTeam, awayTeam, stadiumName, homeScore, awayScore, expectedAttendance);
    }
}
