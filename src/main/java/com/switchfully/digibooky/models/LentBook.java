package com.switchfully.digibooky.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode
public class LentBook {

    private final Book book;
    private final String lendingID;
    private final LocalDate dueDate;


    public LentBook(Book book) {
        this.book = book;
        this.lendingID = UUID.randomUUID().toString();
        this.dueDate = setDueDate();
    }

    private LocalDate setDueDate() {
        return LocalDate.now().plusDays(21);
    }
}
