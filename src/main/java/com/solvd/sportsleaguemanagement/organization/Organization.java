package com.solvd.sportsleaguemanagement.organization;

import com.solvd.sportsleaguemanagement.contracts.Identifiable;
import com.solvd.sportsleaguemanagement.contracts.Payable;

import java.time.Year;
import java.util.Collection;
import java.util.Objects;

public abstract class Organization implements Identifiable {

    private static final int MIN_NAME_LENGTH = 2;

    private Integer id;
    private String name;
    private Year foundedYear;

    static {
        System.out.println("Organization class loaded");
    }

    public Organization(Integer id, String name, Year foundedYear) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        if (!isValidName(name)) throw new IllegalArgumentException("Invalid name");
        this.name = name.trim();
        this.foundedYear = Objects.requireNonNull(foundedYear, "foundedYear cannot be null");
    }

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= MIN_NAME_LENGTH;
    }

    public String summary() {
        return String.format("Organization: id=%d, name='%s', foundedYear=%s", id, name, foundedYear);
    }

    public Integer getIdNumber() {
        return id;
    }

    @Override
    public String getId() {
        return String.valueOf(id);
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

    public Year getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Year foundedYear) {
        this.foundedYear = Objects.requireNonNull(foundedYear, "foundedYear cannot be null");
    }

    public void runPayroll(Collection<? extends Payable> staff) {
        if (staff == null) return;
        for (Payable p : staff) {
            double salary = p.getSalary();
            p.pay(salary);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organization that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return summary();
    }
}
