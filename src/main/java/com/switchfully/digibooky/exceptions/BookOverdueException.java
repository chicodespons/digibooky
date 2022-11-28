package com.switchfully.digibooky.exceptions;

public class BookOverdueException extends IllegalArgumentException {

    public BookOverdueException() {
        this("Book is returned too late, pay up now!!!");
    }

    public BookOverdueException(String s) {
        super(s);
    }
}
