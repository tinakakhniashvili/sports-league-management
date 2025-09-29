package com.solvd.sportsleaguemanagement.exception;

public class OverbookingException extends Exception {

    public OverbookingException(String message) {
        super(message);
    }

    public OverbookingException(String message, Throwable cause) {
        super(message, cause);
    }

}
