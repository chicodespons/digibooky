package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.dto.BookToUpdateToDto;
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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getAllBooks(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_BOOKS);
        return bookService.getAllBooks();
    }

    @GetMapping(path = "/getbookbyisbn")
    @ResponseStatus(HttpStatus.OK)
    public BookSummaryDto getBookByISBN(@RequestHeader String authorization, @RequestParam(required = false) String search) {
        securityService.validateAuthorization(authorization, Feature.GET_BOOK_BY_ISBN);
         return bookService.getBookByISBN(search);
    }

    @PutMapping(value = "/updatebook/{isbn}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookDto updateProfessor(@RequestBody BookToUpdateToDto book, @PathVariable String isbn, @RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.UPDATE_BOOK);
        return bookService.updateBook(book, isbn);
    }
}
