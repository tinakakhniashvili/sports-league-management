package organizations;

import java.math.BigDecimal;
import java.time.LocalDate;

public class League extends Organization {

    private static final int MAX_TEAMS = 50;

    private String sportType;
    private short seasonYear;
    private int numberOfTeams;
    private LocalDate seasonStartDate;
    private BigDecimal prizeFund;
    private Team[] teams;

    static {
        System.out.println("League class loaded");
    }

    public League(int id, String name, int foundedYear,
                  String sportType, short seasonYear, int numberOfTeams,
                  LocalDate seasonStartDate, BigDecimal prizeFund, Team[] teams) {
        super(id, name, foundedYear);
        this.sportType = sportType;
        this.seasonYear = seasonYear;
        this.numberOfTeams = numberOfTeams;
        this.seasonStartDate = seasonStartDate;
        this.prizeFund = prizeFund;
        this.teams = teams;
    }

    public static boolean isValidTeamCount(int count) {
        return count > 1 && count <= MAX_TEAMS;
    }

    public void scheduleSeason() {
        System.out.println("Scheduling " + sportType + " league " + seasonYear +
                " for " + getName() + " with " + numberOfTeams + " teams.");
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public short getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(short seasonYear) {
        this.seasonYear = seasonYear;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    public LocalDate getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(LocalDate seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public BigDecimal getPrizeFund() {
        return prizeFund;
    }

    public void setPrizeFund(BigDecimal prizeFund) {
        this.prizeFund = prizeFund;
    }

    public Team[] getTeams() {
        return teams;
    }

    public void setTeams(Team[] teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", sportType='" + sportType + '\'' +
                ", seasonYear=" + seasonYear +
                ", numberOfTeams=" + numberOfTeams +
                ", seasonStartDate=" + seasonStartDate +
                ", prizeFund=" + prizeFund;
    }
}
