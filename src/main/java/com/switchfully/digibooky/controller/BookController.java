package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.security.SecurityService;
import com.switchfully.digibooky.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/books")
public class BookController {

    private final BookService bookService;
    private final SecurityService securityService;

    public BookController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    //Get all books   http://localhost:8080/books
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooks(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_BOOKS);
        return bookService.getAllBooks();
    }

    //Get book by isbn   http://localhost:8080/books/getbooksbyisbn
    @GetMapping(path = "/getbookbyisbn")
    @ResponseStatus(HttpStatus.OK)
    public List<BookSummaryDto> getBookByISBN(@RequestHeader String authorization, @RequestParam(required = false) String search) {
        securityService.validateAuthorization(authorization, Feature.GET_BOOK_BY_ISBN);
         return bookService.getBookByISBN(search);
    }

    //Update book     http://localhost:8080/books/updatebook/{isbn}
    @PutMapping(value = "/updatebook/{isbn}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateProfessor(@RequestBody BookToUpdateToDto book, @PathVariable String isbn, @RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.UPDATE_BOOK);
        return bookService.updateBook(book, isbn);
    }

    //Registere new book   http://localhost:8080/books/addbook
    @PostMapping(path = "/addbook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Book registerNewBook(@RequestHeader String authorization, @RequestBody BookDto book) {
        securityService.validateAuthorization(authorization, Feature.REGISTER_BOOK);
        return bookService.registerNewBook(book);
    }

    @GetMapping(path = "/getbookbytitle")
    @ResponseStatus(HttpStatus.OK)
    public List<BookSummaryDto> getBookByTitle(@RequestHeader String authorization, @RequestParam(required = false) String search) {
        securityService.validateAuthorization(authorization, Feature.GET_BOOK_BY_TITLE);
        return bookService.getBookByTitle(search);
    }

    @GetMapping(path = "/getbookbyauthor")
    @ResponseStatus(HttpStatus.OK)
    public List<BookSummaryDto> getBookByAuthor(@RequestHeader String authorization, @RequestParam(required = false) String search) {
        securityService.validateAuthorization(authorization, Feature.GET_BOOK_BY_AUTHOR);
        return bookService.getBookByAuthor(search);
    }





    // Borrow a book
//    @GetMapping(path = "/borrowbook")
//    @ResponseStatus(HttpStatus.OK)
//    public List<BookDto> getBookBy
}
