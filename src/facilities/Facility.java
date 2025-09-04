package facilities;

public class Facility {

    private static final int MIN_NAME_LENGTH = 3;

    private int id;
    private String name;
    private String location;

    static {
        System.out.println("Facility class loaded");
    }

    public Facility(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= MIN_NAME_LENGTH;
    }

    public void open() {
        System.out.println("Facility " + name + " at " + location + " is now open.");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Facility: " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'';
    }
}
