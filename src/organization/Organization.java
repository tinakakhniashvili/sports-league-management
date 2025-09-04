package organization;

import java.time.Year;

public class Organization {

    private static final int MIN_NAME_LENGTH = 2;

    private int id;
    private String name;
    private Year foundedYear;

    static {
        System.out.println("Organization class loaded");
    }

    public Organization(int id, String name, Year foundedYear) {
        this.id = id;
        this.name = name;
        this.foundedYear = foundedYear;
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= MIN_NAME_LENGTH;
    }

    public String summary() {
        return "Organization: id=" + id +
                ", name='" + name + '\'' +
                ", foundedYear=" + foundedYear;
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

    public Year getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Year foundedYear) {
        this.foundedYear = foundedYear;
    }

    @Override
    public String toString() {
        return summary();
    }
}
