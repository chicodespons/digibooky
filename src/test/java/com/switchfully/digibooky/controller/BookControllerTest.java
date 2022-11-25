package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

@SpringBootTest
class BookControllerTest {
    @Autowired
    private BookController bookController;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;




}