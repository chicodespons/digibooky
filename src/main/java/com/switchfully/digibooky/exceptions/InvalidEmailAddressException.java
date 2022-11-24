package com.switchfully.digibooky.exceptions;

public class InvalidEmailAddressException extends Exception {
    public InvalidEmailAddressException() {
        this("The email address you provided is already in use.");
    }

    public InvalidEmailAddressException(String message) {
        super(message);
    }
}
