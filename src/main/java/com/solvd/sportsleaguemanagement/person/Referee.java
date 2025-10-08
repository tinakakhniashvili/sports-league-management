package com.solvd.sportsleaguemanagement.person;

import com.solvd.sportsleaguemanagement.contracts.Payable;
import com.solvd.sportsleaguemanagement.exception.InvalidIdException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Objects;

public class Referee extends Person implements Payable {

    private static final Logger LOGGER = LogManager.getLogger(Referee.class);

    private String certificationLevel;
    private int matchesOfficiated;
    private double matchFee;

    public Referee(Integer id, String name, String surname, LocalDate birthDate,
                   String certificationLevel, int matchesOfficiated) {
        super(id, name, surname, birthDate);
        setCertificationLevel(certificationLevel);
        if (matchesOfficiated < 0) throw new IllegalArgumentException("Matches officiated cannot be negative");
        this.matchesOfficiated = matchesOfficiated;
        this.matchFee = 0.0;
    }

    @Override
    public String getRole() {
        return "REFEREE";
    }

    @Override
    public double discountRate() {
        return 0.00;
    }

    public void officiateMatch() {
        LOGGER.info("{} is officiating a match.", fullName());
    }

    @Override
    public double getSalary() {
        return matchFee;
    }

    @Override
    public void pay(double amount) {
        LOGGER.info("Paid {:.2f} to referee {}", amount, fullName());
    }

    public void setMatchFee(double fee) {
        if (fee < 0) throw new IllegalArgumentException("fee < 0");
        this.matchFee = fee;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        if (certificationLevel == null || certificationLevel.isBlank()) {
            throw new InvalidIdException("Referee certification cannot be blank.");
        }
        String cl = certificationLevel.trim();
        if (!cl.matches("(FIFA|UEFA)\\s+.*")) {
            throw new InvalidIdException("Invalid certification level: " + cl);
        }
        this.certificationLevel = cl;
    }

    public int getMatchesOfficiated() {
        return matchesOfficiated;
    }

    public void setMatchesOfficiated(int matchesOfficiated) {
        if (matchesOfficiated < 0) throw new IllegalArgumentException("Matches officiated cannot be negative");
        this.matchesOfficiated = matchesOfficiated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Referee)) return false;
        if (!super.equals(o)) return false;
        Referee referee = (Referee) o;
        return matchesOfficiated == referee.matchesOfficiated && Objects.equals(certificationLevel, referee.certificationLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), certificationLevel, matchesOfficiated);
    }

    @Override
    public String toString() {
        return String.format("%s, certificationLevel='%s', matchesOfficiated=%d",
                super.toString(), certificationLevel, matchesOfficiated);
    }
}
