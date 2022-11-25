package com.switchfully.digibooky.exceptions;

public class BookByTitleNotFoundException extends IllegalArgumentException {

    public BookByTitleNotFoundException(String s) {
        super(s);
    }
}
