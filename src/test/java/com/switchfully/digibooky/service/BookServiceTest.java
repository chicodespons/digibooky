package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.exceptions.IsbnAlreadyExistsException;
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


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
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
        List<Book> bookToFindList = new ArrayList<>();
        bookToFindList.add(new Book("124444444","Goblet of Fire",new Author("lola", "lolita"),"Magic and goblet to catch", false));
        //given
        bookRepository.addBookList(bookToFindList);
        String ISBN = "124444444";
        //then
        Assertions.assertEquals(bookMapper.toBookSummaryDto(bookToFindList), bookService.getBookByISBN(ISBN));

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
        String ISBN = "aaaa";
        //then
        Assertions.assertThrows(BookByISBNNotFoundException.class, () -> bookService.getBookByISBN(ISBN));
    }

    @Test
    @DisplayName("Testing find book by Title when given good title")
    void givenTitle_whenGivenGoodTitle_getListOfBooks(){

        List<Book> bookToFindList = new ArrayList<>();
        bookToFindList.add(new Book("124444444","Ramon",new Author("lola", "lolita"),"Magic and goblet to catch", false));
        //given
        bookRepository.addBookList(bookToFindList);
        String title = "Ramon";
        //then
        Assertions.assertEquals(bookMapper.toBookSummaryDto(bookToFindList), bookService.getBookByTitle(title));


    }


    @Test
    @DisplayName("When updating book to new book as librarian, we should check that book has the same fields of the new book while keeping the original isbn")
    void givenBookInRepository_whenUpdatingBookToNewBook_bookInRepositoryShouldHaveNewBooksFields(){
        // given
        Book bookToChange = new Book("1234","Goblet of Fire",new Author("lola", "lolita"),"Magic and goblet to catch", false);
        BookToUpdateToDto bookToUpdateTo = new BookToUpdateToDto("The secret Room",new Author("lola", "lolita"),"Magic and goblet to catch", true);
        bookRepository.addBook(bookToChange);
        //when
        bookService.updateBook(bookToUpdateTo,bookToChange.getISBN());
        // then
        assertEquals(bookToUpdateTo.getTitle(), bookToChange.getTitle());
        assertEquals(bookToUpdateTo.isHidden(), bookToChange.isHidden());
        assertEquals("1234", bookToChange.getISBN());
    }

    @Test
    @DisplayName("When adding a book with unique ISBN book should present in repository")
    void when_addingNewBook_shouldBeAlsoPresent_inRepository() {
        //given
        BookDto bookToRegister = new BookDto("999", "The Hobbit", new Author("Piet", "Hein"));
        Book bookInRepository = new Book(bookToRegister.getISBN(), bookToRegister.getTitle(), bookToRegister.getAuthor());
        //when
        bookService.registerNewBook(bookToRegister);
        //then
        Assertions.assertTrue(bookRepository.getBookList().contains(bookInRepository));
    }

    @Test
    @DisplayName("When adding a book with not unique ISBN, should throw exception")
    void when_addingABookWithNoUniqueISBN_shouldThrowException() {
        //given
        BookDto bookToRegister = new BookDto("999", "The Hobbit", new Author("Piet", "Hein"));
        //when
        bookService.registerNewBook(bookToRegister);
        //then
        Assertions.assertThrows(IsbnAlreadyExistsException.class, ()-> bookService.registerNewBook(bookToRegister));

    }
    @Test
    @DisplayName("When adding a book with not unique ISBN, should be correct")
    void when_addingABookWithNoUniqueISBN_shouldBeCorrect() {
        //given
        BookDto bookToRegister = new BookDto("999", "The Hobbit", new Author("Piet", "Hein"));
        //when
        bookService.registerNewBook(bookToRegister);
        Throwable throwAnException = catchThrowable(()-> bookService.registerNewBook(bookToRegister));
        //then
        Assertions.assertEquals("Book can't be registered ISBN already exists in database",throwAnException.getMessage());

    }

}