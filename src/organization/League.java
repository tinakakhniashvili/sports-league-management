package organization;

import java.time.LocalDate;
import java.time.Year;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class League extends Organization {

    private static final int MAX_TEAMS = 50;
    public static final BigDecimal TAX_RATE;

    private String sportType;
    private Year seasonYear;
    private LocalDate seasonStartDate;
    private final Set<Team> teams = new HashSet<>();

    static {
        TAX_RATE = new BigDecimal("0.18");
        System.out.println("League class loaded, TAX_RATE initialized to 18%");
    }

    public League(Integer id, String name, Year foundedYear, String sportType, Year seasonYear) {
        super(id, name, foundedYear);
        this.sportType = Objects.requireNonNull(sportType, "sportType cannot be null");
        this.seasonYear = Objects.requireNonNull(seasonYear, "seasonYear cannot be null");
    }

    public static boolean isValidTeamCount(int count) {
        return count >= 1 && count <= MAX_TEAMS;
    }

    protected void scheduleSeason() {
        System.out.println(String.format(
                "Scheduling %s league %s for %s with %d teams.",
                sportType, seasonYear, getName(), getNumberOfTeams()
        ));
    }

    public static BigDecimal applyTax(BigDecimal net) {
        return net.multiply(BigDecimal.ONE.add(TAX_RATE))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public int getNumberOfTeams() {
        return teams.size();
    }

    public void addTeam(Team t) {
        Objects.requireNonNull(t, "team cannot be null");
        if (!isValidTeamCount(teams.size() + 1)) throw new IllegalArgumentException("Invalid team count");
        teams.add(t);
    }

    public Set<Team> getTeams() {
        return Set.copyOf(teams);
    }

    public String getSportType() {
        return sportType;
    }

    public void setSportType(String sportType) {
        this.sportType = Objects.requireNonNull(sportType, "sportType cannot be null");
    }

    public Year getSeasonYear() {
        return seasonYear;
    }

    protected void setSeasonYear(Year seasonYear) {
        this.seasonYear = Objects.requireNonNull(seasonYear, "seasonYear cannot be null");
    }

    public LocalDate getSeasonStartDate() {
        return seasonStartDate;
    }

    public void setSeasonStartDate(LocalDate seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof League)) return false;
        if (!super.equals(o)) return false;
        League league = (League) o;
        return Objects.equals(sportType, league.sportType)
                && Objects.equals(seasonYear, league.seasonYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sportType, seasonYear);
    }

    @Override
    public String toString() {
        return String.format(
                "%s, sportType='%s', seasonYear=%s, seasonStartDate=%s, teams=%d",
                super.toString(), sportType, seasonYear, seasonStartDate, getNumberOfTeams()
        );
    }
}
