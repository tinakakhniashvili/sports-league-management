package facility;

import java.math.BigDecimal;
import java.util.Objects;

public class TrainingGround extends Facility {

    private static final int MIN_FIELDS = 1;

    private boolean indoor;
    private int fieldsCount;

    static {
        System.out.println("TrainingGround class loaded");
    }

    public TrainingGround(Integer id, String name, String location, boolean indoor, int fieldsCount) {
        super(id, name, location);
        if (!isValidFieldsCount(fieldsCount)) throw new IllegalArgumentException("Invalid fields count");
        this.indoor = indoor;
        this.fieldsCount = fieldsCount;
    }

    @Override
    public BigDecimal getSeatFeePerTicket() {
        return new BigDecimal(indoor ? "1.50" : "1.00");
    }

    public static boolean isValidFieldsCount(int count) {
        return count >= MIN_FIELDS;
    }

    public void conductTraining() {
        System.out.println(String.format("%s training ground is conducting training on %d fields.", getName(), fieldsCount));
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
        if (!isValidFieldsCount(fieldsCount)) throw new IllegalArgumentException("Invalid fields count");
        this.fieldsCount = fieldsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainingGround)) return false;
        if (!super.equals(o)) return false;
        TrainingGround that = (TrainingGround) o;
        return indoor == that.indoor && fieldsCount == that.fieldsCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), indoor, fieldsCount);
    }

    @Override
    public String toString() {
        return String.format("%s, indoor=%b, fieldsCount=%d", super.toString(), indoor, fieldsCount);
    }
}
