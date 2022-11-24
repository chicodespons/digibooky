package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookMapperTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

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
}