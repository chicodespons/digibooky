package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.exceptions.*;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public BookSummaryDto getBookByISBN(String ISBN) {
        List<Book> bookList = bookRepository.getBookList();
        Book book = bookList.stream()
                .filter(b -> b.getISBN().equals(ISBN))
                .findFirst()
                .orElseThrow(() -> new BookByISBNNotFoundException("No book found for given ISBN"));
        return bookMapper.toBookSummaryDto(book);
    }

    public BookDto updateBook(BookToUpdateToDto book, String isbn) {
        Book foundBook = bookRepository.getBookList().stream()
                .filter(books -> books.getISBN().equalsIgnoreCase(isbn))
                .findFirst()
                .orElseThrow(() -> new BookByISBNNotFoundException("No book found for given ISBN"));
        foundBook.setTitle(book.getTitle());
        foundBook.setAuthor(book.getAuthor());
        foundBook.setSummary(book.getSummary());
        foundBook.setHidden(book.isHidden());
        return bookMapper.toDto(foundBook);
    }

    public Book registerNewBook(BookDto book) {
        List<Book> bookList = bookRepository.getBookList();
        for (Book bookInBookList : bookList) {
            if (bookInBookList.getISBN().equals(book.getISBN())) {
                throw new IsbnAlreadyExistsException("Book can't be registered ISBN already exists in database");
            }
        }
        Book bookToRegister = new Book(book.getISBN(), book.getTitle(), book.getAuthor());
        bookRepository.addBook(bookToRegister);
        return bookToRegister;
    }
}
