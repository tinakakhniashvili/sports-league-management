package person;

import contracts.Payable;
import java.time.LocalDate;
import java.util.Objects;

public class Referee extends Person implements Payable {

    private String certificationLevel;
    private int matchesOfficiated;

    private double matchFee;

    public Referee(Integer id, String name, String surname, LocalDate birthDate,
                   String certificationLevel, int matchesOfficiated) {
        super(id, name, surname, birthDate);
        this.certificationLevel = Objects.requireNonNull(certificationLevel, "certificationLevel cannot be null").trim();
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
        System.out.println(String.format("%s is officiating a match.", fullName()));
    }

    @Override
    public double getSalary() {
        return matchFee;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid %.2f to referee %s%n", amount, fullName());
    }

    public void setMatchFee(double fee) {
        if (fee < 0) throw new IllegalArgumentException("fee < 0");
        this.matchFee = fee;
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = Objects.requireNonNull(certificationLevel, "certificationLevel cannot be null").trim();
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
