package contracts;

import person.Person;

public interface Bookable {
    boolean isAvailable();
    void book(Person by);
    void cancel();
}
