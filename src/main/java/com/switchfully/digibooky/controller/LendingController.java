package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.BookDto;
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
    @PostMapping(path = "/returnbook/{leningId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public BookDto returnBook(@RequestHeader String authorization, @PathVariable String leningId) {
        securityService.validateAuthorization(authorization, Feature.RETURN_BOOK);
        return lendingService.returnBook(leningId);
    }

//    @PostMapping(path = "{nookIsbn}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public LentBook lendBook(@RequestHeader String authorization, @PathVariable String bookIsbn) {
//        return lendingService.lendBook(authorization, bookIsbn);
//    }
}
