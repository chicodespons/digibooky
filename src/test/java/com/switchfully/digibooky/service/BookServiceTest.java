package com.switchfully.digibooky.service;

import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookServiceTest {


    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;


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