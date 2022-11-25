package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.exceptions.BookNotAvailableException;
import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.dto.LentBookDto;
import com.switchfully.digibooky.dto.LentBookOverdueDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.mapper.LentBookMapper;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.models.User;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LentBookRepository;
import com.switchfully.digibooky.repository.UserRepository;
import com.switchfully.digibooky.security.SecurityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class LendingService {
    private final LentBookRepository lentBookRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final SecurityService securityService;
    private final LentBookMapper lentBookMapper;




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

    public LentBook lendBook(String authorization, String bookIsbn) throws BookNotAvailableException {
        String userId = securityService.getUserIdByAuthorizationString(authorization);
        Optional<User> user = userRepository.getUserById(userId);
        if (user.isEmpty()) {
            throw new IncorrectLogInInformationException();
        }

        Optional<Book> book = bookRepository.getBookByISBN(bookIsbn);
        if (book.isEmpty()) {
            throw new BookByISBNNotFoundException("The ISNB you provided could not be found.");
        }
        if (book.get().isHidden()) {
            throw new BookNotAvailableException();
        }
        book.get().setHidden(true);
        LentBook bookToLend = new LentBook(book.get(), user.get());
        return lentBookRepository.lendBook(bookToLend);
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
}
