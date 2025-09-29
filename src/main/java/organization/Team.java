package organization;

import common.annotations.Auditable;
import contracts.Trainable;
import exception.PlayerNotEligibleException;
import person.Coach;
import person.Player;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Auditable("Team entity")
public class Team extends Organization {

    private static final int MAX_PLAYERS = 30;

    private final List<Trainable> members = new ArrayList<>();
    private final List<Player> roster = new ArrayList<>();

    private String city;
    private Division division;
    private Coach headCoach;

    @Auditable("Disciplinary points field")
    private int disciplinaryPoints;

    static {
        System.out.println("Team class loaded");
    }

    public enum Division {EAST, WEST, NORTH, SOUTH}

    public Team(Integer id, String name, Year foundedYear, String city) {
        super(id, name, foundedYear);
        this.city = Objects.requireNonNull(city, "city cannot be null");
    }

    public static boolean isValidPlayersCount(int count) {
        return count > 0 && count <= MAX_PLAYERS;
    }

    public void playMatch() {
        System.out.printf(
                "%s from %s is playing a match with %d players.%n",
                getName(), city, getPlayersCount()
        );
    }

    public final void printTeamInfo() {
        System.out.println(this);
    }

    public void addPlayer(Player player) {
        if (player == null) throw new PlayerNotEligibleException("Null player.");
        if (!player.isEligibleToPlay()) {
            throw new PlayerNotEligibleException("Player not eligible to play: " + player.fullName());
        }
        if (roster.size() >= MAX_PLAYERS) {
            throw new PlayerNotEligibleException("Roster full (" + MAX_PLAYERS + ").");
        }
        boolean dup = roster.stream()
                .anyMatch(p -> p.equals(player) || p.getJerseyNumber() == player.getJerseyNumber());
        if (dup) {
            throw new PlayerNotEligibleException("Player not eligible to play: " + player.fullName());
        }
        roster.add(player);
    }

    public void addMember(Trainable t) {
        if (t != null) members.add(t);
    }

    public List<Trainable> getMembers() {
        return List.copyOf(members);
    }

    public void groupTraining() {
        members.stream()
                .forEach(Trainable::train);
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

    public List<Player> getRoster() {
        return List.copyOf(roster);
    }

    public void setRoster(Collection<Player> players) {
        roster.clear();
        if (players == null) return;
        if (!isValidPlayersCount(players.size())) {
            throw new PlayerNotEligibleException("Invalid player count");
        }
        players.stream()
                .forEach(this::addPlayer);
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
        return roster.size();
    }

    public List<String> getRosterNames() {
        return roster
                .stream()
                .map(Player::fullName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team team)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(city, team.city) && division == team.division;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), city, division);
    }

    @Override
    public String toString() {
        return String.format(
                "%s, city='%s', headCoach=%s, division=%s, playersCount=%d, disciplinaryPoints=%d",
                super.toString(),
                city,
                (headCoach != null ? headCoach.fullName() : "none"),
                (division != null ? division : "none"),
                getPlayersCount(),
                disciplinaryPoints
        );
    }
}
