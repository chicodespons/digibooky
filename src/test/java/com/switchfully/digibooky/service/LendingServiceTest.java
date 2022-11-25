package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.CreateMemberDto;
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
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LendingServiceTest {
    @Autowired
    LendingService lendingService;
    @Autowired
    private LentBookRepository lentBookRepository;
    @Autowired
    private BookRepository bookRepository;

//    @Test
//    void LendingBookWithWrongLogin_ShouldReturn_IncorrectLogInInformationException() {
//        String authorization = new String(Base64.getEncoder().encode("loic@email.com:a"));
//        CreateMemberDto member2 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "@email.com", "Stationstraat",  10, 1000, "Brussel");
//
//        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewMember(member);});
//        assertTrue(exception.getMessage().contains("Invalid email format. Email must be of format X@X.X"));
//    }



//@Test
//@DisplayName("If lentBook is returned, it should be removed form LentBookRepository")
//void whenLendingBook_isReturned_itShouldBeRemoved_fromLendingRepository() {
//  //give
//  Book book = new Book("LendingBookTest1", "Title", new Author("Kwak", "Kwek"));
//  bookRepository.addBook(book);
//  LentBook lentBook = new LentBook(book,
//          new User("hablakojd,o", "Bloem", "Karel", "bloemen@ggmail.be", Role.MEMBER));
//  //when
//  lentBookRepository.addLentBook(lentBook);
//  lendingService.returnBook(lentBook.getLendingID());
//  //then
//  Assertions.assertFalse(lentBookRepository.getAllBooks().contains(lentBook));
//  Assertions.assertFalse(book.isHidden());
//
//}

//@Test:
//@DisplayName()

}