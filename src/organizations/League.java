package organizations;

public class League extends Organization {
    private String sportType;
    private int seasonYear;
    private int numberOfTeams;

    private static final int MAX_TEAMS = 50;

    static {
        System.out.println("League class loaded");
    }

    public League(int id, String name, int foundedYear, String sportType, int seasonYear, int numberOfTeams) {
        super(id, name, foundedYear);
        this.sportType = sportType;
        this.seasonYear = seasonYear;
        this.numberOfTeams = numberOfTeams;
    }

    public static boolean isValidTeamCount(int count) {
        return count > 1 && count <= MAX_TEAMS;
    }

    public void scheduleSeason() {
        System.out.println("Scheduling " + sportType + " league " + seasonYear + " for " + getName() + " with " + numberOfTeams + " teams.");
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = sportType;
    }

    public int getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(int seasonYear) {
        this.seasonYear = seasonYear;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", sportType='" + sportType + '\'' +
                ", seasonYear=" + seasonYear +
                ", numberOfTeams=" + numberOfTeams;
    }
}
