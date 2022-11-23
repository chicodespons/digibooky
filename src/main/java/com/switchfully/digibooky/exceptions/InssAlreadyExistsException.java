package com.switchfully.digibooky.exceptions;

public class InssAlreadyExistsException extends Exception {
    public InssAlreadyExistsException() {
        this("The INSS you provided has already been used.");
    }

    public InssAlreadyExistsException(String message) {
        super(message);
    }
}
