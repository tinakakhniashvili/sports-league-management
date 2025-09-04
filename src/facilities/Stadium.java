package facilities;

public class Stadium extends Facility {

    private static final int MIN_CAPACITY = 1000;

    private int capacity;
    private float pitchSlopePercent;
    private char sectorLetter;
    private String surfaceType;

    static {
        System.out.println("Stadium class loaded");
    }

    public Stadium(int id, String name, String location, int capacity, String surfaceType,
                   float pitchSlopePercent, char sectorLetter) {
        super(id, name, location);
        this.capacity = capacity;
        this.surfaceType = surfaceType;
        this.pitchSlopePercent = pitchSlopePercent;
        this.sectorLetter = sectorLetter;
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

    public float getPitchSlopePercent() {
        return pitchSlopePercent;
    }

    public void setPitchSlopePercent(float pitchSlopePercent) {
        this.pitchSlopePercent = pitchSlopePercent;
    }

    public char getSectorLetter() {
        return sectorLetter;
    }

    public void setSectorLetter(char sectorLetter) {
        this.sectorLetter = sectorLetter;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", capacity=" + capacity +
                ", surfaceType='" + surfaceType + '\'' +
                ", pitchSlopePercent=" + pitchSlopePercent +
                ", sectorLetter=" + sectorLetter;
    }
}
