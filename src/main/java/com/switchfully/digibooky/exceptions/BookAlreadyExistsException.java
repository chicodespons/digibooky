package com.switchfully.digibooky.exceptions;

public class BookAlreadyExistsException extends IllegalArgumentException {

    public BookAlreadyExistsException(String s) {
        super(s);
    }


}
