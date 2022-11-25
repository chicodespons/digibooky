package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.models.*;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookMapperTest {


    @Autowired
    private BookMapper bookMapper;

    @Test
    @DisplayName("Test from book to BookDto")
    void toDto_whenGivenBook_returnABookDto(){
        //given
        BookDto bookDto = new BookDto("1111", "test", new Author("Jane", "Janeson"));
        Book book = new Book("1111", "test", new Author("Jane", "Janeson"),"this is a testbook", false);
        Assertions.assertEquals(bookDto, bookMapper.toDto(book));
    }

    @Test
    @DisplayName("Test from bookDto to Book")
    void toBook_whenGivenBookDto_returnABook(){
        BookDto bookDto = new BookDto("1111", "test", new Author("Jane", "Janeson"));
        Book book = new Book("1111", "test", new Author("Jane", "Janeson"),"No summary available", false);
        Assertions.assertEquals(book, bookMapper.toBook(bookDto));
    }

    @Test
    @DisplayName("Testing from book to BookSummaryDto")
    void toBookSummaryDto_whenGivenBook_getBookSummaryDto() {
        //Given
        BookSummaryDto bookSummaryDto = new BookSummaryDto("1111", "test", new Author("Jane", "Janeson"), "this is a testbook");
        Book book = new Book("1111", "test", new Author("Jane", "Janeson"),"this is a testbook", false);
        //When

        //Then
        Assertions.assertEquals(bookSummaryDto, bookMapper.toBookSummaryDto(book));
    }

    @Test
    @DisplayName("Testing from listofbooks to list of BookSummaryDtos")
    void toBookSummaryDto_whenGivenBookList_getListofBookSummaryDtos(){
        //Given
        List<BookSummaryDto> bookSummaryDtoList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        bookSummaryDtoList.add(new BookSummaryDto("1111", "test", new Author("Jane", "Janeson"), "this is a testbook"));
        bookSummaryDtoList.add(new BookSummaryDto("2222", "test2", new Author("Jano", "Janoson"), "this is a testbook2"));
        bookList.add(new Book("1111", "test", new Author("Jane", "Janeson"), "this is a testbook", false));
        bookList.add(new Book("2222", "test2", new Author("Jano", "Janoson"), "this is a testbook2", true));
        //When
        //then
        Assertions.assertEquals(bookSummaryDtoList, bookMapper.toBookSummaryDto(bookList));

    }



}