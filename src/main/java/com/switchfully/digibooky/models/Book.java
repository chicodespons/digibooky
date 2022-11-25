package com.switchfully.digibooky.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Setter
public class Book {

    private String ISBN;

    private String title;

    private Author author;

    private String summary;

    private boolean isHidden;


    public Book(String isbn, String title, Author author, String summary, boolean isHidden) {
        ISBN = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isHidden = isHidden;
    }

    public Book(String isbn, String title, Author author, boolean isHidden) {
        this(isbn, title, author, "No summary available", isHidden);
    }

    public Book(String isbn, String title, Author author) {
        this(isbn, title, author, "No summary available", false);
    }


}
