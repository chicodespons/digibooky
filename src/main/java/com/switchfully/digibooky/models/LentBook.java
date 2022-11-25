package com.switchfully.digibooky.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class LentBook {

    private final Book book;
    private final String lendingID;
    private LocalDate dueDate;
    private final User user;

    public LentBook(Book book, User user) {
        this.book = book;
        this.lendingID = UUID.randomUUID().toString();
        setDueDate();
        this.user = user;
    }

    private void setDueDate() {
        dueDate = LocalDate.now();
    }
}
