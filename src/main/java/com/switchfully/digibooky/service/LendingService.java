package com.switchfully.digibooky.service;

import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.repository.LentBookRepository;
import org.springframework.stereotype.Service;

@Service
public class LendingService {
    private final LentBookRepository lentBookRepository;
    private final BookMapper bookMapper;

    public LendingService(LentBookRepository lentBookRepository, BookMapper bookMapper) {
        this.lentBookRepository = lentBookRepository;
        this.bookMapper = bookMapper;
    }

    public List<LentBook> getAllLentBooks() {
        return lentBookRepository.getAllBooks();
    }

//    public LentBook lendBook(String userId, String bookIsbn) {
//        return lentBookRepository.lendBook();
//    }

    //    As a member I want to be able to borrow a book, so that I can allocate a book to myself for a certain duration.
//
//    The member's user identification number and the book's ISBN should be provided.
//    A unique lending identification number and a due date should be registered, by default this date is TODAY + 3 WEEKS
//    A book can only be lent once at a time.
}
