package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.models.User;
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
                .map(this::toDto)
                .toList();
    }

    public Book toBook(BookDto bookDto) {
        return new Book(bookDto.getISBN(), bookDto.getTitle(), bookDto.getAuthor());
    }

    public BookSummaryDto toBookSummaryDto(Book book) {
        return new BookSummaryDto(book.getISBN(), book.getTitle(), book.getAuthor(), book.getSummary(),
                book.isHidden());
    }

    public List<BookSummaryDto> toBookSummaryDto(Collection<Book> bookCollection) {
        return bookCollection.stream()
                .map(this::toBookSummaryDto)
                .toList();
    }

    public LentBook toLentBook(Book book, User user) {
        return new LentBook(book, user);
    }
}
