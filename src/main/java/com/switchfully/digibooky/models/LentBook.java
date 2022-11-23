package com.switchfully.digibooky.models;

import java.time.LocalDate;
import java.util.Objects;

public class LentBook {

    private final Book book;
    private final String LendingID;
    private final LocalDate dueDate;


    public LentBook(Book book, String lendingID, LocalDate dueDate) {
        this.book = book;
        LendingID = lendingID;
        this.dueDate = dueDate;
    }

    public Book getBook() {
        return book;
    }

    public String getLendingID() {
        return LendingID;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "LentBook{" +
                "book=" + book +
                ", LendingID='" + LendingID + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LentBook lentBook)) return false;
        return Objects.equals(book, lentBook.book) && Objects.equals(LendingID, lentBook.LendingID) && Objects.equals(dueDate, lentBook.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, LendingID, dueDate);
    }
}
