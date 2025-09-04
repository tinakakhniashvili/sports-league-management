package person;

import java.time.LocalDate;

public class Referee extends Person {

    private String certificationLevel;
    private int matchesOfficiated;

    public Referee(int id, String name, String surname, LocalDate birthDate, String certificationLevel, int matchesOfficiated) {
        super(id, name, surname, birthDate);
        this.certificationLevel = certificationLevel;
        this.matchesOfficiated = matchesOfficiated;
    }

    public void officiateMatch() {
        System.out.println(fullName() + " is officiating a match.");
    }

    public String getCertificationLevel() {
        return certificationLevel;
    }

    public void setCertificationLevel(String certificationLevel) {
        this.certificationLevel = certificationLevel;
    }

    public int getMatchesOfficiated() {
        return matchesOfficiated;
    }

    public void setMatchesOfficiated(int matchesOfficiated) {
        this.matchesOfficiated = matchesOfficiated;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", certificationLevel='" + certificationLevel + '\'' +
                ", matchesOfficiated=" + matchesOfficiated;
    }
}
