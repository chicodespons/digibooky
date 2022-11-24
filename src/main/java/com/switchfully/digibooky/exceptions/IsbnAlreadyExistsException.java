package com.switchfully.digibooky.exceptions;

public class IsbnAlreadyExistsException extends IllegalArgumentException{

    public IsbnAlreadyExistsException(String s) {
        super(s);
    }
}
