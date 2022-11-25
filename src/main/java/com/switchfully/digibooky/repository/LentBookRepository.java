package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LentBookRepository {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final Map<User, List<LentBook>> lentBookList = new HashMap<>();

    public LentBookRepository(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        mocking();
    }

    public void mocking() {
        LentBook lentBook = new LentBook(bookRepository.getBookList().get(0));
        List<LentBook> mockLentBookList = List.of(lentBook);
        lentBookList.put(userRepository.getUser("loic@email.com"), mockLentBookList);
    }

}
