package facility;

public class TrainingGround extends Facility {

    private static final int MIN_FIELDS = 1;

    private boolean indoor;
    private int fieldsCount;

    static {
        System.out.println("TrainingGround class loaded");
    }

    public TrainingGround(int id, String name, String location, boolean indoor, int fieldsCount) {
        super(id, name, location);
        this.indoor = indoor;
        this.fieldsCount = fieldsCount;
    }

    public static boolean isValidFieldsCount(int count) {
        return count >= MIN_FIELDS;
    }

    public void conductTraining() {
        System.out.println(getName() + " training ground is conducting training on " + fieldsCount + " fields.");
    }

    public boolean isIndoor() {
        return indoor;
    }

    public void setIndoor(boolean indoor) {
        this.indoor = indoor;
    }

    public int getFieldsCount() {
        return fieldsCount;
    }

    public void setFieldsCount(int fieldsCount) {
        this.fieldsCount = fieldsCount;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", indoor=" + indoor +
                ", fieldsCount=" + fieldsCount;
    }
}
