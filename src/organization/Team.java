package organization;

import person.Coach;
import person.Player;

import java.time.Year;

public class Team extends Organization {

    private static final int MAX_PLAYERS = 30;

    private String city;
    private Coach headCoach;
    private Player[] roster;
    private int disciplinaryPoints;
    private Division division;

    static {
        System.out.println("Team class loaded");
    }

    public enum Division { EAST, WEST, NORTH, SOUTH }

    public Team(Integer id, String name, Year foundedYear, String city) {
        super(id, name, foundedYear);
        this.city = city;
        this.roster = new Player[0];
    }

    public static boolean isValidPlayersCount(int count) {
        return count > 0 && count <= MAX_PLAYERS;
    }

    public void playMatch() {
        System.out.println(getName() + " from " + city +
                " is playing a match with " + getPlayersCount() + " players.");
    }

    public void addPlayer(Player player) {
        if (player == null) return;
        Player[] next = new Player[roster.length + 1];
        System.arraycopy(roster, 0, next, 0, roster.length);
        next[roster.length] = player;
        roster = next;
    }

    public void addDisciplinaryPoints(int delta) {
        this.disciplinaryPoints += delta;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(Coach headCoach) {
        this.headCoach = headCoach;
    }

    public Player[] getRoster() {
        return roster;
    }

    public void setRoster(Player[] roster) {
        this.roster = roster != null ? roster : new Player[0];
    }

    public int getDisciplinaryPoints() {
        return disciplinaryPoints;
    }

    public void setDisciplinaryPoints(int disciplinaryPoints) {
        this.disciplinaryPoints = disciplinaryPoints;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public int getPlayersCount() {
        return roster.length;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", city='" + city + '\'' +
                ", headCoach=" + (headCoach != null ? headCoach.fullName() : "none") +
                ", division=" + (division != null ? division : "none") +
                ", playersCount=" + getPlayersCount() +
                ", disciplinaryPoints=" + disciplinaryPoints;
    }
}
