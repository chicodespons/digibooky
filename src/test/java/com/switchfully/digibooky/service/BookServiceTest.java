package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.AuthorRepository;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;


    @Test
    @DisplayName("Testing method get all books, to see that it returns a list containing all book in repository")
    void checkingThatGetAllBooks_returnsListOfDtoBooks() {
        //given
        Book bookOne = new Book("198165","Samson & Gert",authorRepository.getAuthorList().get(2),"Weird talking dog and rich owner", false);
        Book bookTwo = new Book("25000","Samson & Gert",authorRepository.getAuthorList().get(2),"Weird talking dog and rich owner", false));
        bookRepository.add(bookOne);
        bookRepository.add(bookTwo);

        //then
        Assertions.assertTrue(bookService.getAllBooks().contains(bookMapper.toDto(bookOne)));
        Assertions.assertTrue(bookService.getAllBooks().contains(bookMapper.toDto(bookTwo)));
    }

}