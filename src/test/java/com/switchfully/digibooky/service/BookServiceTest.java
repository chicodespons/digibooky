package com.switchfully.digibooky.service;

import com.switchfully.digibooky.models.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;



    @Test
    void givenISBN_whenGivenGoodISBN_getBookByISBN() {
    }
}