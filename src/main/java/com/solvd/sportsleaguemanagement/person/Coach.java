package com.solvd.sportsleaguemanagement.person;

import com.solvd.sportsleaguemanagement.contracts.Payable;
import com.solvd.sportsleaguemanagement.contracts.Trainable;

import java.time.LocalDate;
import java.util.Objects;

public class Coach extends Person implements Payable, Trainable {

    private int experienceYears;
    private String licenseLevel;

    private double salary;

    public Coach(Integer id, String name, String surname, LocalDate birthDate,
                 int experienceYears, String licenseLevel) {
        super(id, name, surname, birthDate);
        if (experienceYears < 0) throw new IllegalArgumentException("Experience years cannot be negative");
        this.experienceYears = experienceYears;
        this.licenseLevel = Objects.requireNonNull(licenseLevel, "licenseLevel cannot be null").trim();
        this.salary = 0.0;
    }

    @Override
    public String getRole() {
        return "COACH";
    }

    @Override
    public double discountRate() {
        return 0.05;
    }

    public void trainTeam() {
        System.out.println(String.format("%s is training the team.", fullName()));
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid %.2f to coach %s%n", amount, fullName());
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("salary < 0");
        this.salary = salary;
    }

    @Override
    public void train() {
        System.out.printf("Coach %s conducts a training session%n", fullName());
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        if (experienceYears < 0) throw new IllegalArgumentException("Experience years cannot be negative");
        this.experienceYears = experienceYears;
    }

    public String getLicenseLevel() {
        return licenseLevel;
    }

    public void setLicenseLevel(String licenseLevel) {
        this.licenseLevel = Objects.requireNonNull(licenseLevel, "licenseLevel cannot be null").trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        if (!super.equals(o)) return false;
        Coach coach = (Coach) o;
        return experienceYears == coach.experienceYears && Objects.equals(licenseLevel, coach.licenseLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experienceYears, licenseLevel);
    }

    @Override
    public String toString() {
        return String.format("%s, experienceYears=%d, licenseLevel='%s'",
                super.toString(), experienceYears, licenseLevel);
    }
}
