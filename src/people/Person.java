package people;

public class Person {

    private static final int MIN_AGE = 18;

    private int id;
    private String name;
    private String surname;
    private int age;

    static {
        System.out.println("Person class loaded");
    }

    public Person(int id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public static boolean isValidAge(int age ) {
        return age >= MIN_AGE;
    }

    public String fullName(){
        return name + " " + surname;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person: id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age;
    }
}
