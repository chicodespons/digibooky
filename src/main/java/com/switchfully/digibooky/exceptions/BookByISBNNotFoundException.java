package com.switchfully.digibooky.exceptions;

public class BookByISBNNotFoundException extends IllegalArgumentException {

    public BookByISBNNotFoundException(String s) {
        super(s);
    }
}
