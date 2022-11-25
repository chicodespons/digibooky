package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.LentBookDto;
import com.switchfully.digibooky.dto.LentBookOverdueDto;
import com.switchfully.digibooky.exceptions.BookNotAvailableException;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.security.SecurityService;
import com.switchfully.digibooky.service.LendingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "lend")
public class LendingController {
    private final LendingService lendingService;
    private final SecurityService securityService;

    public LendingController(LendingService lendingService, SecurityService securityService) {
        this.lendingService = lendingService;
        this.securityService = securityService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<LentBook> getAllLentBooks() {
        return lendingService.getAllLentBooks();
    }

    //Return a book
    @PostMapping(path = "/returnbook/{leningId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookDto returnBook(@RequestHeader String authorization, @PathVariable String leningId) {
        securityService.validateAuthorization(authorization, Feature.RETURN_BOOK);
        return lendingService.returnBook(leningId, authorization);
    }

    @PostMapping(path = "{bookIsbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public LentBook lendBook(@RequestHeader String authorization, @PathVariable String bookIsbn) throws BookNotAvailableException {
        securityService.validateAuthorization(authorization, Feature.LEND_BOOK);
        return lendingService.lendBook(authorization, bookIsbn);
    }

    @GetMapping(path = "/lentbooksbymember/{memberEmail}")
    @ResponseStatus(HttpStatus.OK)
    public List<LentBookDto> getAllLentBooksByMember(@RequestHeader String authorization, @PathVariable String memberEmail){
        securityService.validateAuthorization(authorization, Feature.GET_ALL_LENT_BOOK_BY_MEMBER);
        return lendingService.getAllLentBooksByMember(memberEmail);
    }

    @GetMapping(path = "/overduebooks")
    @ResponseStatus(HttpStatus.OK)
    public List<LentBookOverdueDto> getAllOverDueBooks(@RequestHeader String authorization){
        securityService.validateAuthorization(authorization, Feature.GET_ALL_OVERDUE_BOOKS);
        return lendingService.getAllOverDueBooks();
    }
}
