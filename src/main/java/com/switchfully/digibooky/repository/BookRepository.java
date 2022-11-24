package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

@Repository
public class BookRepository {

    private final AuthorRepository authorRepository;


    private final List<Book> bookList = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
        mocking();
    }

    public void mocking(){
        bookList.add(new Book("123456","Goblet of Fire",authorRepository.getAuthorList().get(0),"Magic and goblet to catch", false));
        bookList.add(new Book("148984","Pippi Langkous",authorRepository.getAuthorList().get(1),"Weird girl with super strenght", false));
        bookList.add(new Book("198165","Samson & Gert",authorRepository.getAuthorList().get(2),"Weird talking dog and rich owner", false));
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book bookToRegister) {
        bookList.add(bookToRegister);
    }

//    public Book getBook(String isbn) {
//        return bookMap.get(isbn);
//    }
//
//    private List<Book> getBooks() {
//        List<Book> books = new ArrayList<>();
//        for (Book book : bookMap.values()) {
//            books.add(book);
//        }
//        return books;
//    }
//
//    public boolean isbnAlreadyExistsException(String isbn) {
//        List<Book> bookList = getBooks();
//        for (Book book : bookList) {
//            if (book.getISBN().equals(isbn)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void save(Book book) {
//        bookMap.put(book.getISBN());
//    }
}
