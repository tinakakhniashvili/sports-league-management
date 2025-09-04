package organization;

import java.time.LocalDate;
import java.time.Year;

public class League extends Organization {

    private static final int MAX_TEAMS = 50;

    private String sportType;
    private Year seasonYear;
    private LocalDate seasonStartDate;
    private Team[] teams;

    static {
        System.out.println("League class loaded");
    }

    public League(int id, String name, Year foundedYear,
                  String sportType, Year seasonYear) {
        super(id, name, foundedYear);
        this.sportType = sportType;
        this.seasonYear = seasonYear;
        this.teams = new Team[0];
    }

    public static boolean isValidTeamCount(int count) {
        return count > 1 && count <= MAX_TEAMS;
    }

    public void scheduleSeason() {
        System.out.println("Scheduling " + sportType + " league " + seasonYear +
                " for " + getName() + " with " + getNumberOfTeams() + " teams.");
    }

    public int getNumberOfTeams() {
        return teams.length;
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public Year getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(Year seasonYear) {
        this.seasonYear = seasonYear;
    }

    public LocalDate getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(LocalDate seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
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
                ", seasonStartDate=" + seasonStartDate +
                ", teams=" + getNumberOfTeams();
    }
}
