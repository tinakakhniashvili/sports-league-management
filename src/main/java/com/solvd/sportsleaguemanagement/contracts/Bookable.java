package com.solvd.sportsleaguemanagement.contracts;

import com.solvd.sportsleaguemanagement.person.Person;

public interface Bookable {

    boolean isAvailable();

    void book(Person by);

    void cancel();

}

