package organizations;

import people.Coach;
import people.Player;

public class Team extends Organization {

    private static final int MAX_PLAYERS = 30;

    private String city;
    private Coach headCoach;
    private Player[] roster;
    private byte disciplinaryPoints;
    private char division;
    private int playersCount;

    static {
        System.out.println("Team class loaded");
    }

    public Team(int id, String name, int foundedYear, String city,
                Coach headCoach, Player[] roster,
                byte disciplinaryPoints, char division) {
        super(id, name, foundedYear);
        this.city = city;
        this.headCoach = headCoach;
        this.roster = roster;
        this.disciplinaryPoints = disciplinaryPoints;
        this.division = division;
        this.playersCount = roster != null ? roster.length : 0;
    }

    public static boolean isValidPlayersCount(int count) {
        return count > 0 && count <= MAX_PLAYERS;
    }

    public void playMatch() {
        System.out.println(getName() + " from " + city + " is playing a match with " + playersCount + " players.");
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
        this.roster = roster;
        this.playersCount = roster != null ? roster.length : 0;
    }

    public byte getDisciplinaryPoints() {
        return disciplinaryPoints;
    }

    public void setDisciplinaryPoints(byte disciplinaryPoints) {
        this.disciplinaryPoints = disciplinaryPoints;
    }

    public char getDivision() {
        return division;
    }

    public void setDivision(char division) {
        this.division = division;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", city='" + city + '\'' +
                ", headCoach=" + (headCoach != null ? headCoach.fullName() : "none") +
                ", division=" + division +
                ", playersCount=" + playersCount +
                ", disciplinaryPoints=" + disciplinaryPoints;
    }
}
