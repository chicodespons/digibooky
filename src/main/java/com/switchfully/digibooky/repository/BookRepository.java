package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookRepository {

    private final AuthorRepository authorRepository;


    private final List<Book> bookList = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        mocking();
    }

    public void mocking() {
        bookList.add(new Book("123456", "Goblet of Fire", authorRepository.getAuthorList().get(0), "Magic and goblet to catch", false));
        bookList.add(new Book("148984", "Pippi Langkous", authorRepository.getAuthorList().get(1), "Weird girl with super strenght", false));
        bookList.add(new Book("198165", "Samson & Gert", authorRepository.getAuthorList().get(2), "Weird talking dog and rich owner", false));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBookList(List<Book> books) {

        bookList.addAll(books);
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public Optional<Book> getBookByISBN(String bookIsbn) {
        return bookList.stream()
                .filter(book -> book.getISBN().equals(bookIsbn))
                .findFirst();
    }
}
