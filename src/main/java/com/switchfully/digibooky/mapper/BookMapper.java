package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.models.dto.BookDto;
import com.switchfully.digibooky.models.dto.BookSummaryDto;
import com.switchfully.digibooky.models.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class BookMapper {
    public BookDto toDto(Book book) {
        return new BookDto(book.getISBN(), book.getTitle(), book.getAuthor());
    }

    public List<BookDto> toDto(Collection<Book> bookCollection) {
        return bookCollection.stream()
                .map(book -> toDto(book))
                .toList();
    }

    public Book toBook(BookDto bookDto) {
        return new Book(bookDto.getISBN(), bookDto.getTitle(), bookDto.getAuthor());
    }

    public BookSummaryDto toBookSummaryDto(Book book) {

        return new BookSummaryDto(book.getISBN(), book.getTitle(), book.getAuthor(), book.getSummary());

    }
}
