package com.switchfully.digibooky.service;

import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.models.LentBook;
import com.switchfully.digibooky.repository.BookRepository;
import com.switchfully.digibooky.repository.LentBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LendingServiceTest {

@Autowired
private LendingService lendingService;

@Autowired
private LentBookRepository lentBookRepository;



@Test
@DisplayName("If lentBook is returned, it should be removed form LentBookRepository")
void whenLendingBook_isReturned_itShouldBeRemoved_fromLendingRepository() {
    //give
  LentBook
    lentBookRepository.getAllBooks();
    //when
    LendingService.removeBook(lentBookInRepository.getLedingID());
    //then
    Assertions.assertTrue(lentBookInRepository.getAllBooks().contains(lentBookInRepository));
    assterFalse(lentBookInRepository.isHidden());


}
//  LentBookRepository lentBookInRepository = new Book("124445544","Trees",new Author("Karel", "Vanboom"),"Tress do talk", false);


@Test:
@DisplayName()



}