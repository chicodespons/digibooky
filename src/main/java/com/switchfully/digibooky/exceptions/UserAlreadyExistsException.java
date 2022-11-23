package com.switchfully.digibooky.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
        this("The email address you provided is already in use.");
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
