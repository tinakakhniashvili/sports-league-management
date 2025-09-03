package organizations;

public class Team extends Organization {
    private String city;
    private String coachName;
    private int playersCount;

    private static final int MAX_PLAYERS = 30;

    static {
        System.out.println("Team class loaded");
    }

    public Team(int id, String name, int foundedYear, String city, String coachName, int playersCount) {
        super(id, name, foundedYear);
        this.city = city;
        this.coachName = coachName;
        this.playersCount = playersCount;
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

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public void setPlayersCount(int playersCount) {
        this.playersCount = playersCount;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", city='" + city + '\'' +
                ", coachName='" + coachName + '\'' +
                ", playersCount=" + playersCount;
    }
}
