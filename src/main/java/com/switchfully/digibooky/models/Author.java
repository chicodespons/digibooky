package com.switchfully.digibooky.models;

import java.util.Objects;

public class Author {

    private final String lastname;
    private final String firstname;

    public Author(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author author)) return false;
        return Objects.equals(lastname, author.lastname) && Objects.equals(firstname, author.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname);
    }

    @Override
    public String toString() {
        return "Author{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
}
