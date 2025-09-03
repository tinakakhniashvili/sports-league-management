package people;

public class Coach extends Person {
    private int experienceYears;
    private String licenseLevel;

    public Coach(int id, String name, String surname, int age, int experienceYears, String licenseLevel) {
        super(id, name, surname, age);
        this.experienceYears = experienceYears;
        this.licenseLevel = licenseLevel;
    }

    public void trainTeam(){
        System.out.println(fullName() + " is training the team.");
    }

    public int getExperienceYears() {
        return experienceYears;
    }
    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
    public String getLicenseLevel() {
        return licenseLevel;
    }
    public void setLicenseLevel(String licenseLevel) {
        this.licenseLevel = licenseLevel;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", experienceYears=" + experienceYears +
                ", licenseLevel=" + licenseLevel;
    }
}
