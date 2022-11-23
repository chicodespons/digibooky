package com.switchfully.digibooky.controller.exceptions;

public class BookByISBNNotFoundException extends IllegalArgumentException{

    public BookByISBNNotFoundException(String s) {
        super(s);
    }
}
