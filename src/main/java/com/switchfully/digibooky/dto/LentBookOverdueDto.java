package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.Book;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class LentBookOverdueDto {
    private final Book book;
    private final String lendingID;
    private LocalDate dueDate;
    private final String email;

    public LentBookOverdueDto(Book book, String lendingID, LocalDate dueDate, String email) {
        this.book = book;
        this.lendingID = lendingID;
        this.dueDate = dueDate;
        this.email = email;
    }

}