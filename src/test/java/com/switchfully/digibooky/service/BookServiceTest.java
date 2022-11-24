package com.switchfully.digibooky.service;

import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.AuthorRepository;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)


class BookServiceTest {


    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    @DisplayName("Testing find book by ISBN when given good ISBN")
    void givenISBN_whenGivenGoodISBN_getBookByISBN() {
        Book bookToFind = new Book("124444444","Goblet of Fire",new Author("lola", "lolita"),"Magic and goblet to catch", false);
        //given
        bookRepository.addBook(bookToFind);
        String ISBN = "124444444";
        //then
        Assertions.assertEquals(bookMapper.toBookSummaryDto(bookToFind), bookService.getBookByISBN(ISBN));

    }
    @Test
    @DisplayName("Testing method get all books, to see that it returns a list containing all book in repository")
    void checkingThatGetAllBooks_returnsListOfDtoBooks() {
        //given
        Book bookOne = new Book("198165","Samson & Gert",authorRepository.getAuthorList().get(2),"Weird talking dog and rich owner", false);
        Book bookTwo = new Book("25000","Samson & Gert",authorRepository.getAuthorList().get(2),"Weird talking dog and rich owner", false);
        bookRepository.addBook(bookOne);
        bookRepository.addBook(bookTwo);
        //then
        Assertions.assertTrue(bookService.getAllBooks().contains(bookMapper.toDto(bookOne)));
        Assertions.assertTrue(bookService.getAllBooks().contains(bookMapper.toDto(bookTwo)));
    }
    @Test
    @DisplayName("Testing find book by ISBN when given bad ISBN")
    void givenISBN_whenGivenBadISBN_throwException(){
        Book bookToFind = new Book("124444444","Goblet of Fire",new Author("lola", "lolita"),"Magic and goblet to catch", false);
        //given
        bookRepository.addBook(bookToFind);
        String ISBN = "124444";
        //then
        Assertions.assertThrows(BookByISBNNotFoundException.class, () -> bookService.getBookByISBN(ISBN));
    }




}