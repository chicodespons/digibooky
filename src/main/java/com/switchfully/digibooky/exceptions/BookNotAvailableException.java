package com.switchfully.digibooky.exceptions;

public class BookNotAvailableException extends Exception {
    public BookNotAvailableException() {
        this("The book you asked for is not available.");
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
