package facilities;

public class Stadium extends Facility {
    private int capacity;
    private String surfaceType;

    private static final int MIN_CAPACITY = 1000;

    static {
        System.out.println("Stadium class loaded");
    }

    public Stadium(int id, String name, String location, int capacity, String surfaceType) {
        super(id, name, location);
        this.capacity = capacity;
        this.surfaceType = surfaceType;
    }

    public static boolean isValidCapacity(int capacity) {
        return capacity >= MIN_CAPACITY;
    }

    public void hostMatch() {
        System.out.println(getName() + " stadium is hosting a match with capacity " + capacity + ".");
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", capacity=" + capacity +
                ", surfaceType='" + surfaceType + '\'';
    }
}
