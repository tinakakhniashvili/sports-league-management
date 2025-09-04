package person;

import java.time.LocalDate;

public class Person {

    private static final int MIN_AGE = 18;

    private int id;
    private String name;
    private String surname;
    private LocalDate birthDate;

    static {
        System.out.println("Person class loaded");
    }

    public Person(int id, String name, String surname, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
    }

    public static boolean isValidAge(int age) {
        return age >= MIN_AGE;
    }

    public String fullName() {
        return name + " " + surname;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Person: id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + getAge();
    }
}
