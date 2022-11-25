package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.service.LendingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "lend")
public class LendingController {
    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<LentBook> getAllLentBooks() {
        return lendingService.getAllLentBooks();
    }

//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.OK)
//    public LentBook lendBook(@RequestBody String userId, String bookIsbn) {
//        return lendingService.lendBook();
//    }
}
