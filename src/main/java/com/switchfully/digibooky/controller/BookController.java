package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.security.SecurityService;
import com.switchfully.digibooky.service.BookService;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
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

    @GetMapping(path = "/")
    @ResponseStatus(HttpStatus.OK)
    public BookDto getBookByISBN(@RequestHeader String authorization, @RequestParam(required = false) String search) {
        securityService.validateAuthorization(authorization, Feature.GET_BOOK_BY_ISBN);
         return bookService.getBookByISBN(search);
    }

    @PostMapping(path = "/addbook", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Book registerNewBook(@RequestHeader String authorization, @RequestBody BookDto book) {
        securityService.validateAuthorization(authorization, Feature.REGISTER_BOOK);
        return bookService.registerNewBook(book);
    }


}
