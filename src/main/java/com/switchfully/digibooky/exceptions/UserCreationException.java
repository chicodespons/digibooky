package com.switchfully.digibooky.exceptions;

public class UserCreationException extends Exception {
    public UserCreationException() {
        this("Some required fields where not filled (correctly).");
    }

    public UserCreationException(String message) {
        super(message);
    }
}
