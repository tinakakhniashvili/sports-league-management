package facility;

import contracts.Bookable;
import contracts.Identifiable;
import exception.FacilityUnavailableException;
import person.Person;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Facility implements Bookable, Identifiable {

    private static final int MIN_NAME_LENGTH = 3;

    private Integer id;
    private String name;
    private String location;

    private boolean available = true;
    private Person bookedBy;

    static {
        System.out.println("Facility class loaded");
    }

    public Facility(Integer id, String name, String location) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        if (!isValidName(name)) throw new IllegalArgumentException("Invalid name");
        this.name = name.trim();
        this.location = location != null ? location.trim() : null;
    }

    public abstract BigDecimal getSeatFeePerTicket();

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= MIN_NAME_LENGTH;
    }

    public void open() {
        System.out.println(String.format("Facility %s at %s is now open.", name, location));
    }

    public Integer getIdNumber() {
        return id;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    protected void setId(Integer id) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
    }

    protected void setName(String name) {
        if (!isValidName(name)) throw new IllegalArgumentException("Invalid name");
        this.name = name.trim();
    }

    public String getLocation() {
        return location;
    }

    protected void setLocation(String location) {
        this.location = location != null ? location.trim() : null;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void book(Person by) {
        if (!available) throw new FacilityUnavailableException("Already booked");
        this.available = false;
        this.bookedBy = Objects.requireNonNull(by, "by cannot be null");
    }

    @Override
    public void cancel() {
        this.available = true;
        this.bookedBy = null;
    }

    public Person getBookedBy() {
        return bookedBy;
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
