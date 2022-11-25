package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.models.User;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
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
        Book bookToLend = bookRepository.getBookList().get(0);
        bookToLend.setHidden(true);
        LentBook lentBook = new LentBook(bookToLend, userRepository.getUser("loic@email.com"));
        lentBookList.put(lentBook.getLendingID(), lentBook);
    }

    public List<LentBook> getAllBooks() {
        return lentBookList.values().stream()
                .toList();
    }

    public LentBook getLendingBookById(String lendingID) {
        return lentBookList.get(lendingID);
    }

    public void removeLending(String lendingID) {
        lentBookList.remove(lendingID);
    }

    public LentBook lendBook(LentBook lentBook) {
        lentBookList.put(lentBook.getLendingID(), lentBook);
        return lentBookList.get(lentBook.getLendingID());
    }
}
