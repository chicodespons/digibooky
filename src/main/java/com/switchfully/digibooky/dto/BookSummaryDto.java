package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.Author;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class BookSummaryDto {

    private String ISBN;

    private String title;

    private Author author;

    private String summary;
    private boolean isHidden;

    // private final boolean isHidden; indien nodig.

    public BookSummaryDto(String isbn, String title, Author author, String summary, boolean isHidden) {
        ISBN = isbn;
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isHidden = isHidden;
    }


}
