package com.switchfully.digibooky.exceptions;

public class BookByAuthorNotFoundException extends IllegalArgumentException{

    public BookByAuthorNotFoundException(String s) {
        super(s);
    }
}
