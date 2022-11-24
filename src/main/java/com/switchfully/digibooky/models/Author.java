package com.switchfully.digibooky.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
public class Author {

    private final String lastname;
    private final String firstname;

    public Author(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

}
