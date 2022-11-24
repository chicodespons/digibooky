package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.Author;
import lombok.Getter;

@Getter
public class BookToUpdateToDto {
    private final String title;

    private final Author author;

    private final String summary;
    private final boolean isHidden;

    public BookToUpdateToDto(String title, Author author, String summary, boolean isHidden) {
        this.title = title;
        this.author = author;
        this.summary = summary;
        this.isHidden = isHidden;
    }

}
