package com.switchfully.digibooky.models;

import java.util.Objects;

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

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isHidden() {
        return isHidden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return isHidden == book.isHidden && Objects.equals(ISBN, book.ISBN) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(summary, book.summary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, title, author, summary, isHidden);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ISBN='" + ISBN + '\'' +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", summary='" + summary + '\'' +
                ", isHidden=" + isHidden +
                '}';
    }
}
