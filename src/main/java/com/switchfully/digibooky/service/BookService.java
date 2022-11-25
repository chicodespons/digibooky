package com.switchfully.digibooky.service;
import com.switchfully.digibooky.providers.RegexProvider;
import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.exceptions.*;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.dto.BookDto;
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
        List<Book> booksFound = bookList.stream().filter(b -> RegexProvider.isContain(b.getTitle().toLowerCase(), title.toLowerCase())).toList();
        if(!booksFound.isEmpty()) {
            return bookMapper.toBookSummaryDto(booksFound);
        } else
            throw new BookByTitleNotFoundException("Book not found for given Title");
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
