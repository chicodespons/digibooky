package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.BookSummaryDto;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.security.SecurityService;
import com.switchfully.digibooky.service.BookService;
import lombok.val;
import org.springframework.http.HttpStatus;
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
//
//    @GetMapping(path = "/getbookbytitle")
//    @ResponseStatus(HttpStatus.OK)
//    public BookSummaryDto getBookByTitle(@RequestHeader String)
}
