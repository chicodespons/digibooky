package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
public class LentBookDto {
    private final Book book;
    private final String lendingID;
    private LocalDate dueDate;
    private String email;

    public LentBookDto(Book book, String lendingID, LocalDate dueDate, String email) {
        this.book = book;
        this.lendingID = lendingID;
        this.dueDate = dueDate;
        this.email = email;
    }


}
