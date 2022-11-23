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
public class BookDto {

        private String ISBN;

        private String title;

        private Author author;


        // private final boolean isHidden; indien nodig.

        public BookDto(String isbn, String title, Author author) {
            ISBN = isbn;
            this.title = title;
            this.author = author;

        }


}
