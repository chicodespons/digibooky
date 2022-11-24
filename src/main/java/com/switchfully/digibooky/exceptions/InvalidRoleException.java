package com.switchfully.digibooky.exceptions;

public class InvalidRoleException extends Exception {
    public InvalidRoleException() {
        this("The role your provided could not be found.");
    }

    public InvalidRoleException(String message) {
        super(message);
    }
}
