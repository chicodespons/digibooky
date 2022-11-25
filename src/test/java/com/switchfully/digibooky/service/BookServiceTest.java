package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.exceptions.BookByAuthorNotFoundException;
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
        bookToFindList.add(new Book("15","Ramon",new Author("lola", "lolita"),"Magic and goblet to catch", false));
        bookRepository.addBookList(bookToFindList);
        String title = "Ramon";
        //then
        Assertions.assertEquals(bookMapper.toBookSummaryDto(bookToFindList), bookService.getBookByTitle(title));


    }

    @Test
    @DisplayName("When finding book by author, given good author return list of booksummary")
    void getBookByAuthor_givenGoodAuthor_returnListOfBooks(){
        //Given
        List<Book> bookToFindList = new ArrayList<>();
        bookToFindList.add(new Book("77","Ronny",new Author("Ron", "Ronest"),"Ronny his book", false));
        //given
        bookRepository.addBookList(bookToFindList);
        String author = "Ron";
        //then
        Assertions.assertEquals(bookMapper.toBookSummaryDto(bookToFindList), bookService.getBookByAuthor(author));
    }

    @Test
    @DisplayName("When finding book by author, given bad author return exception")
    void getBookByAuthor_givenBadAuthor_throwException(){
        //Given
        List<Book> bookToFindList = new ArrayList<>();
        bookToFindList.add(new Book("47855998","HarrySnarry",new Author("Harry", "Snarry"),"Harry Snarries book", false));
        //given
        bookRepository.addBookList(bookToFindList);
        String author = "123";
        //then
        Assertions.assertThrows(BookByAuthorNotFoundException.class, () -> bookService.getBookByAuthor(author));
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
        BookDto bookToRegister = new BookDto("956161891651", "The Hobbit", new Author("Piet", "Hein"));
        //when
        bookService.registerNewBook(bookToRegister);
        //then
        Assertions.assertThrows(IsbnAlreadyExistsException.class, ()-> bookService.registerNewBook(bookToRegister));

    }
    @Test
    @DisplayName("When adding a book with not unique ISBN, should be correct")
    void when_addingABookWithNoUniqueISBN_shouldBeCorrect() {
        //given
        BookDto bookToRegister = new BookDto("78945615191", "The Hobbit", new Author("Piet", "Hein"));
        //when
        bookService.registerNewBook(bookToRegister);
        Throwable throwAnException = catchThrowable(()-> bookService.registerNewBook(bookToRegister));
        //then
        Assertions.assertEquals("Book can't be registered ISBN already exists in database",throwAnException.getMessage());

    }


    @Test
    @DisplayName("When soft deleting a book, book should be hidden and still in book repository")
    void whenSoftDeletingABook_bookShouldBeHidden_butStillInRepository(){
        //given
        Book bookInRepository = new Book("124444451915951951944","Ramon",new Author("lola", "lolita"),"Magic and goblet to catch", false);
        bookRepository.addBook(bookInRepository);
        //when
        bookService.deleteBookByIsbn(bookInRepository.getISBN());
        //then
        assertTrue(bookInRepository.isHidden());
        Assertions.assertTrue(bookRepository.getBookList().contains(bookInRepository));
    }
    @Test
    @DisplayName("When soft deleting a book by an Isbn that is not valid, should throw exception")
    void whenSoftDeletingAbookWithUnknownISBN_exceptionShouldBeThrown(){
        //then
        Assertions.assertThrows(BookByISBNNotFoundException.class,()-> bookService.deleteBookByIsbn("This is not a good isbn"));
    }
    @Test
    @DisplayName("When undeleting a book, it should be no longer hidden")
    void whenUndeletingADeleteBook_itShouldNoLongerBeHidden_andShowUpInTheBookDTOList(){
        //given
        Book bookInRepository = new Book("124444444","Ramon",new Author("lola", "lolita"),"Magic and goblet to catch", false);
        bookRepository.addBook(bookInRepository);
        bookService.deleteBookByIsbn(bookInRepository.getISBN());
        //when
        bookService.unDeleteBookByIsbn(bookInRepository.getISBN());
        //then
        assertFalse(bookInRepository.isHidden());
    }

    @Test
    @DisplayName("Testing method get all books, should not return hidden books")
    void checkingThatGetAllBooks_doesNotreturnsHiddenBooks() {
        //given
        Book bookOne = new Book("this a test isbn: 159489","Test Book",new Author("Sven","Boeck"),"This book is so weird", true);
        //when
        bookRepository.addBook(bookOne);
        //then
        Assertions.assertFalse(bookService.getAllBooks().contains(bookMapper.toDto(bookOne)));
    }
}