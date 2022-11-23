package com.switchfully.digibooky.service;

import com.switchfully.digibooky.models.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;



    @Test
    @DisplayName("Testing find book by ISBN when given good ISBN")
    void givenISBN_whenGivenGoodISBN_getBookByISBN() {

        //given
        String ISBN = "123456";
        //when
        bookService.getBookByISBN(ISBN);
        //then

    }
}