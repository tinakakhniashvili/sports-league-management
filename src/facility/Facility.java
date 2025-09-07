package facility;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Facility {

    private static final int MIN_NAME_LENGTH = 3;

    protected Integer id;
    protected String name;
    protected String location;

    static {
        System.out.println("Facility class loaded");
    }

    public Facility(Integer id, String name, String location) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        if (!isValidName(name)) throw new IllegalArgumentException("Invalid name");
        this.name = name.trim();
        this.location = location != null ? location.trim() : null;
    }

    public abstract BigDecimal seatFeePerTicket();

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= MIN_NAME_LENGTH;
    }

    public void open() {
        System.out.println(String.format("Facility %s at %s is now open.", name, location));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (!isValidName(name)) throw new IllegalArgumentException("Invalid name");
        this.name = name.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location != null ? location.trim() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Facility)) return false;
        Facility that = (Facility) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Facility: id=%d, name='%s', location='%s'", id, name, location);
    }
}