package com.switchfully.digibooky.repository;

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
    private final Map<String, LentBook> lentBookList = new HashMap<>();

    public LentBookRepository(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        mocking();
    }

    public void mocking() {
        LentBook lentBook = new LentBook(bookRepository.getBookList().get(0), userRepository.getUser("loic@email.com"));
        lentBookList.put(lentBook.getLendingID(), lentBook);
    }

    public List<LentBook> getAllBooks() {
        return lentBookList.values().stream()
                .toList();
    }
}
