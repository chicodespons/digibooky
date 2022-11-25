package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.exceptions.InvalidEmailAddressException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.switchfully.digibooky.models.*;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LentBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.switchfully.digibooky.mapper.LentBookMapper;
import com.switchfully.digibooky.models.*;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LentBookRepository;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendingServiceTest {
    @Autowired
    LendingService lendingService;
    @Autowired
    private LentBookRepository lentBookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LentBookMapper lentBookMapper;

     @Autowired
     private UserRepository userRepository;

    @Test
    void LendingBookWithWrongLogin_ShouldReturn_IncorrectLogInInformationException() {
        String primitiveAuthorization = new String(Base64.getEncoder().encode(("loic@email.com:WRONGPASSWORD").getBytes()));
        String authorization = "Basic " + primitiveAuthorization;
        Book book = bookRepository.getBookList().get(0);

        assertThrows(IncorrectLogInInformationException.class, () -> {lendingService.lendBook(authorization, book.getISBN());});
    }

    @Test
    void LendingBookWithWrongISB_ShouldReturn_BookByISBNNotFoundException() {
        String primitiveAuthorization = new String(Base64.getEncoder().encode(("loic@email.com:a").getBytes()));
        String authorization = "Basic " + primitiveAuthorization;

        assertThrows(IncorrectLogInInformationException.class, () -> {lendingService.lendBook(authorization, "1");});
    }

//    @Test
//    void LendingBookWithWrongISB_ShouldReturn_BookByISBNNotFoundException() {
//        String primitiveAuthorization = new String(Base64.getEncoder().encode(("loic@email.com:a").getBytes()));
//        String authorization = "Basic " + primitiveAuthorization;
//
//        assertThrows(IncorrectLogInInformationException.class, () -> {lendingService.lendBook(authorization, "1");});
//    }


    @Test
    @DisplayName("If lentBook is returned, it should be removed form LentBookRepository")
    void whenLendingBook_isReturned_itShouldBeRemoved_fromLendingRepository() {
        //give
        Book book = new Book("LendingBookTest1", "Title", new Author("Kwak", "Kwek"));
        bookRepository.addBook(book);
        User testUser = new User("azerty", "Bloem", "Karel", "sven2@mail.be", Role.MEMBER);
        userRepository.addUser(testUser);
        LentBook lentBook = new LentBook(book,testUser);

        String correctInput = "sven2@mail.be:azerty";
        byte[] inputEncoded = Base64.getEncoder().encode(correctInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);

        //when
        lentBookRepository.addLentBook(lentBook);
        lendingService.returnBook(lentBook.getLendingID(), authorizationString);
        //then
        Assertions.assertFalse(lentBookRepository.getAllBooks().contains(lentBook));
        Assertions.assertFalse(book.isHidden());

    }

//@Test:
//@DisplayName()






//    private Map<String, LentBook> lentBookList;
//    private LentBook lentbook1;
//    private LentBook lentbook2;

//    @BeforeEach
//    public void setUp(){
//        lentBookList = new HashMap<>();
//        lentbook1 =
//                new LentBook(
//                new Book("isbn", "testbook1",
//                        new Author("testy", "tester"), "testsummary",
//                 true), new User("a", "testy", "testony", "test@email.be", Role.MEMBER));
//        lentbook2 = new LentBook(
//                new Book("isbn0000", "testbook10000",
//                        new Author("testy00", "tester00"), "testsummary00",
//                        true), new User("a00", "testy00", "testony00", "test@email.be00", Role.MEMBER));
//
//        lentBookList.put(lentbook1.getLendingID(), lentbook1);
//        lentBookList.put(lentbook2.getLendingID(), lentbook2);
//    }


    @Test
    @DisplayName("Test when member email given, return all books lent by this member")
    void getAllLentBooksByMember_whenGivenEmailMember_returnAllBooksLentByMember(){
        String email = "loic@email.com";

        Assertions.assertEquals(lentBookMapper.lentBookListToDTO(lentBookRepository.getLentBookList().values().stream().toList()), lendingService.getAllLentBooksByMember(email));

    }

//    @Test
//    @DisplayName("Test get alloverdue books")
//    void getAllOverDueBooks_whenAskedForAllOverdueBooks_GiveTheseBooks(){
//
//        Assertions.assertEquals(lentBookMapper.lentBookOverdueDtoList(lentBookRepository.getLentBookList().values().stream().toList()), lendingService.getAllOverDueBooks());
//    }


}