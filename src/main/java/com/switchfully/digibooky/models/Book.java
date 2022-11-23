package com.switchfully.digibooky.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.util.Objects;

@Getter
@ToString
@EqualsAndHashCode
public class Book {

    private final String ISBN;

    private final String title;

    private final Author author;

    private final String summary;

    private final boolean isHidden;


    public Book(String isbn, String title, Author author, String summary, boolean isHidden) {
        ISBN = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isHidden = isHidden;
    }
    public Book(String isbn, String title, Author author, boolean isHidden) {
        this(isbn, title, author, "No summary avaible", isHidden);
    }
    public Book(String isbn, String title, Author author) {
        this(isbn, title, author, "No summary avaible", false);
    }
}
