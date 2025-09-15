package organization;

import contracts.Trainable;
import person.Coach;
import person.Player;
import exception.PlayerNotEligibleException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;

public class Team extends Organization {

    private static final int MAX_PLAYERS = 30;
    private final List<Trainable> members = new ArrayList<>();

    private String city;
    private Division division;
    private Coach headCoach;
    private Player[] roster;

    private int disciplinaryPoints;

    static {
        System.out.println("Team class loaded");
    }

    public enum Division { EAST, WEST, NORTH, SOUTH }

    public Team(Integer id, String name, Year foundedYear, String city) {
        super(id, name, foundedYear);
        this.city = Objects.requireNonNull(city, "city cannot be null");
        this.roster = new Player[0];
    }

    public static boolean isValidPlayersCount(int count) {
        return count > 0 && count <= MAX_PLAYERS;
    }

    public void playMatch() {
        System.out.println(String.format("%s from %s is playing a match with %d players.",
                getName(), city, getPlayersCount()));
    }

    public final void printTeamInfo() {
        System.out.println(this.toString());
    }

    public void addPlayer(Player player) {
        if (player == null) throw new PlayerNotEligibleException("Null player.");
        if (!player.isEligibleToPlay()) throw new PlayerNotEligibleException("Player not eligible to play: " + player.fullName());
        if (roster.length >= MAX_PLAYERS) throw new PlayerNotEligibleException("Roster full (" + MAX_PLAYERS + ").");
        for (Player p : roster) {
            if (p.equals(player) || p.getJerseyNumber() == player.getJerseyNumber()) {
                throw new PlayerNotEligibleException("Duplicate player/jersey: " + player.fullName());
            }
        }
        Player[] next = new Player[roster.length + 1];
        System.arraycopy(roster, 0, next, 0, roster.length);
        next[roster.length] = player;
        roster = Arrays.copyOf(next, next.length);
    }

    public void addMember(Trainable t) {
        if (t != null) members.add(t);
    }

    public List<Trainable> getMembers() {
        return List.copyOf(members);
    }

    public void groupTraining() {
        for (Trainable t : members) {
            t.train();
        }
    }

    public void addDisciplinaryPoints(int delta) {
        if (delta >= 0) this.disciplinaryPoints += delta;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = Objects.requireNonNull(city, "city cannot be null");
    }

    public Coach getHeadCoach() {
        return headCoach;
    }

    public void setHeadCoach(Coach headCoach) {
        this.headCoach = headCoach;
    }

    public Player[] getRoster() {
        return Arrays.copyOf(roster, roster.length);
    }

    public void setRoster(Player[] roster) {
        if (roster != null && !isValidPlayersCount(roster.length)) {
            throw new PlayerNotEligibleException("Invalid player count");
        }
        this.roster = roster != null ? Arrays.copyOf(roster, roster.length) : new Player[0];
    }

    public int getDisciplinaryPoints() {
        return disciplinaryPoints;
    }

    public void setDisciplinaryPoints(int disciplinaryPoints) {
        if (disciplinaryPoints >= 0) this.disciplinaryPoints = disciplinaryPoints;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        if (!super.equals(o)) return false;
        Team team = (Team) o;
        return Objects.equals(city, team.city) && division == team.division;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, division);
    }

    @Override
    public String toString() {
        return String.format("%s, city='%s', headCoach=%s, division=%s, playersCount=%d, disciplinaryPoints=%d",
                super.toString(), city, (headCoach != null ? headCoach.fullName() : "none"),
                (division != null ? division : "none"), getPlayersCount(), disciplinaryPoints);
    }
}
