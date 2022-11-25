package com.switchfully.digibooky.service;

import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.models.User;
import com.switchfully.digibooky.repository.LentBookRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class LendingService {
    private final LentBookRepository lentBookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    public LendingService(LentBookRepository lentBookRepository, UserRepository userRepository, BookMapper bookMapper) {
        this.lentBookRepository = lentBookRepository;
        this.userRepository = userRepository;
        this.bookMapper = bookMapper;
    }

    public List<LentBook> getAllLentBooks() {
        return lentBookRepository.getAllBooks();
    }

//    public LentBook lendBook(String userId, String bookIsbn) {
//        User user = userRepository.getUserById(userId);
//        return lentBookRepository.lendBook();
//    }

    //    As a member I want to be able to borrow a book, so that I can allocate a book to myself for a certain duration.
//
//    The member's user identification number and the book's ISBN should be provided.
//    A unique lending identification number and a due date should be registered, by default this date is TODAY + 3 WEEKS
//    A book can only be lent once at a time.
}
