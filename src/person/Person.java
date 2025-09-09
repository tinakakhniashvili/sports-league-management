package person;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Person {

    private static final int MIN_AGE = 18;

    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    static {
        System.out.println("Person class loaded");
    }

    public Person(Integer id, String name, String surname, LocalDate birthDate) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
        this.surname = Objects.requireNonNull(surname, "surname cannot be null").trim();
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate cannot be null");
        if (!isValidAge(getAge())) throw new IllegalArgumentException("Person must be at least 18");
    }

    public abstract String getRole();
    public abstract double discountRate();

    public static boolean isValidAge(int age) {
        return age >= MIN_AGE;
    }

    public String fullName() {
        return String.format("%s %s", name, surname);
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
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
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = Objects.requireNonNull(surname, "surname cannot be null").trim();
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate cannot be null");
        if (!isValidAge(getAge())) throw new IllegalArgumentException("Person must be at least 18");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person that = (Person) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Person: id=%d, name='%s', surname='%s', birthDate=%s, age=%d",
                id, name, surname, birthDate, getAge());
    }
}