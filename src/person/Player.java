package person;

import contracts.Payable;
import contracts.Trainable;
import java.time.LocalDate;
import java.util.Objects;
import types.Position;

public class Player extends Person implements Payable, Trainable {

    protected Position position;
    private int jerseyNumber;
    private boolean isCaptain;

    private double salary;

    public Player(Integer id, String name, String surname, LocalDate birthDate,
                  Position position, int jerseyNumber, boolean isCaptain) {
        super(id, name, surname, birthDate);
        this.position = Objects.requireNonNull(position, "position cannot be null");
        if (jerseyNumber <= 0) throw new IllegalArgumentException("Jersey number must be positive");
        this.jerseyNumber = jerseyNumber;
        this.isCaptain = isCaptain;
        this.salary = 0.0;
    }

    @Override
    public String getRole() {
        return "PLAYER";
    }

    @Override
    public double discountRate() {
        return 0.10;
    }

    public void scoreGoal() {
        System.out.println(String.format("%s scored a goal!", fullName()));
    }

    public boolean isEligibleToPlay() {
        return isValidAge(getAge());
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("Paid %.2f to player %s%n", amount, fullName());
    }

    public void setSalary(double salary) {
        if (salary < 0) throw new IllegalArgumentException("salary < 0");
        this.salary = salary;
    }

    @Override
    public void train() {
        System.out.printf("Player %s is training%n", fullName());
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = Objects.requireNonNull(position, "position cannot be null");
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        if (jerseyNumber <= 0) throw new IllegalArgumentException("Jersey number must be positive");
        this.jerseyNumber = jerseyNumber;
    }

    public boolean isCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(boolean isCaptain) {
        this.isCaptain = isCaptain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return jerseyNumber == player.jerseyNumber && Objects.equals(position, player.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, jerseyNumber);
    }

    @Override
    public String toString() {
        return String.format("%s, position='%s', jerseyNumber=%d, isCaptain=%b",
                super.toString(), position, jerseyNumber, isCaptain);
    }
}
