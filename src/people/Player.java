package people;

public class Player extends Person {

    private String position;
    private int jerseyNumber;
    private boolean isCaptain;

    public Player(int id, String name, String surname, int age, String position, int jerseyNumber, boolean isCaptain) {
        super(id, name, surname, age);
        this.jerseyNumber = jerseyNumber;
        this.position = position;
        this.isCaptain = isCaptain;
    }

    public void scoreGoal() {
        System.out.println(fullName() + " scored a goal!");
    }

    public boolean isEligibleToPlay() {
        return isValidAge(getAge());
    }

    public void setPosition(String position){
        this.position = position;
    }

    public String getPosition(){
        return this.position;
    }

    public void setJerseyNumber(int jerseyNumber){
        this.jerseyNumber = jerseyNumber;
    }

    public int getJerseyNumber(){
        return this.jerseyNumber;
    }

    public void setIsCaptain(boolean isCaptain){
        this.isCaptain = isCaptain;
    }

    public boolean isCaptain(){
        return this.isCaptain;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", position='" + position + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                ", isCaptain=" + isCaptain;
    }
}
