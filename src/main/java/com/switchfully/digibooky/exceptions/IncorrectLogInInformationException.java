package com.switchfully.digibooky.exceptions;

public class IncorrectLogInInformationException extends RuntimeException {
    public IncorrectLogInInformationException() {
        super("Incorrect login details.");
    }

    public IncorrectLogInInformationException(String message) {
        super(message);
    }
}

