package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.dto.LentBookDto;
import com.switchfully.digibooky.dto.LentBookOverdueDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.mapper.LentBookMapper;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.repository.LentBookRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class LendingService {
    private final LentBookRepository lentBookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;

    private final LentBookMapper lentBookMapper;


    public LendingService(LentBookRepository lentBookRepository, UserRepository userRepository, BookMapper bookMapper, LentBookMapper lentBookMapper) {
        this.lentBookRepository = lentBookRepository;
        this.userRepository = userRepository;
        this.bookMapper = bookMapper;
        this.lentBookMapper = lentBookMapper;
    }

    public List<LentBook> getAllLentBooks() {
        return lentBookRepository.getAllBooks();
    }

    public BookDto returnBook(String lendingID) {
        LentBook lentBook = lentBookRepository.getLendingBookById(lendingID);
        Book book = lentBook.getBook();
        book.setHidden(false);
        lentBookRepository.removeLending(lendingID);
        return bookMapper.toDto(book);
    }

    public List<LentBookDto> getAllLentBooksByMember(String memberEmail) {
        Map<String, LentBook> allLentBooksInMap = lentBookRepository.getLentBookList();
        List<LentBook> lentBooks = allLentBooksInMap.values().stream()
                .filter(book -> book.getUser().getEmail().equals(memberEmail))
                .toList();
        return lentBookMapper.lentBookListToDTO(lentBooks);
    }

    public List<LentBookOverdueDto> getAllOverDueBooks() {
        Map<String, LentBook> allLentBooksInMap = lentBookRepository.getLentBookList();
        List<LentBook> lentOverDueBooks = allLentBooksInMap.values().stream()
                .filter(books -> books.getDueDate().isBefore(LocalDate.now()))
                .toList();
        return lentBookMapper.lentBookOverdueDtoList(lentOverDueBooks);
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
