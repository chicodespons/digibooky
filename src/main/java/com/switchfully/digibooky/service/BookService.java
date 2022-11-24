package com.switchfully.digibooky.service;

import com.switchfully.digibooky.RegexProvider;
import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.exceptions.BookByTitleNotFoundException;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookMapper.toDto(bookRepository.getBookList());
    }

    public List<BookSummaryDto> getBookByISBN(String ISBN) {
        List<Book> bookList = bookRepository.getBookList();
        List<Book> booksFound = bookList.stream().filter(b -> RegexProvider.isContain(b.getISBN(), ISBN)).toList();
        if(!booksFound.isEmpty()) {
            return bookMapper.toBookSummaryDto(booksFound);
        } else
            throw new BookByISBNNotFoundException("Book not found for given ISBN");

    }

    public List<BookSummaryDto> getBookByTitle(String title) {
        List<Book> bookList = bookRepository.getBookList();
        List<Book> booksFound = bookList.stream().filter(b -> RegexProvider.isContain(b.getTitle().toLowerCase(), title)).toList();
        if(!booksFound.isEmpty()) {
            return bookMapper.toBookSummaryDto(booksFound);
        } else
            throw new BookByTitleNotFoundException("Book not found for given Title");
    }
}
