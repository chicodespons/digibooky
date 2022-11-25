package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.exceptions.BookByAuthorNotFoundException;
import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.exceptions.BookByTitleNotFoundException;
import com.switchfully.digibooky.exceptions.IsbnAlreadyExistsException;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.providers.RegexProvider;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return bookMapper.toDto(bookRepository.getBookList()
                .stream()
                .filter(book -> book.isHidden() == false)
                .toList());
    }

    public List<BookSummaryDto> getBookByISBN(String ISBN) {
        List<Book> bookList = bookRepository.getBookList();
        List<Book> booksFound = bookList.stream().filter(b -> RegexProvider.isContain(b.getISBN(), ISBN)).toList();
        if (!booksFound.isEmpty()) {
            return bookMapper.toBookSummaryDto(booksFound);
        } else {
            throw new BookByISBNNotFoundException("Book not found for given ISBN");
        }
    }

    public List<BookSummaryDto> getBookByTitle(String title) {
        List<Book> bookList = bookRepository.getBookList();
        List<Book> booksFound = bookList.stream().filter(b -> RegexProvider.isContain(b.getTitle().toLowerCase(), title.toLowerCase())).toList();
        if (!booksFound.isEmpty()) {
            return bookMapper.toBookSummaryDto(booksFound);
        } else
            throw new BookByTitleNotFoundException("Book not found for given Title");
    }

    public List<BookSummaryDto> getBookByAuthor(String author) {

        List<Book> bookList = bookRepository.getBookList();
        List<String> authors = getAuthorsAsStrings(bookList);

        List<Author> authorMatches = getAuthorMatches(author, authors);

        List<Book> booksThatMatch = getBooksThatMatch(bookList, authorMatches);

        if (!booksThatMatch.isEmpty()) {
            return bookMapper.toBookSummaryDto(booksThatMatch);
        } else
            throw new BookByAuthorNotFoundException("book not found for given author");
    }

    private static List<Book> getBooksThatMatch(List<Book> bookList, List<Author> authorMatches) {
        List<Book> booksThatMatch = new ArrayList<>();

        for (Book book : bookList) {
            for (Author author1 : authorMatches) {
                if (book.getAuthor().equals(author1)) {
                    booksThatMatch.add(book);
                }
            }
        }
        return booksThatMatch;
    }

    private static List<Author> getAuthorMatches(String author, List<String> authors) {
        return authors.stream()
                .filter(a -> RegexProvider.isContain(a.toLowerCase(), author.toLowerCase()))
                .map(a -> new Author(a.substring(a.indexOf(" ") + 1), a.substring(0, a.indexOf(" "))))
                .toList();
    }

    private static List<String> getAuthorsAsStrings(List<Book> bookList) {
        return bookList.stream()
                .map(book -> book.getAuthor().getFirstname() + " " + book.getAuthor().getLastname())
                .toList();
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

    public void deleteBookByIsbn(String isbn) {
        Book foundBook = bookRepository.getBookList().stream()
                .filter(book -> book.getISBN().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookByISBNNotFoundException("No book found for given ISBN"));
        foundBook.setHidden(true);
    }

    public void unDeleteBookByIsbn(String isbn) {
        Book foundBook = bookRepository.getBookList().stream()
                .filter(book -> book.getISBN().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new BookByISBNNotFoundException("No book found for given ISBN"));
        foundBook.setHidden(false);
    }
}
