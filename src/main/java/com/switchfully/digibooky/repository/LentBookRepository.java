package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Author;
import com.switchfully.digibooky.models.LentBook;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LentBookRepository {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;


    private final List<LentBook> lentBookList = new ArrayList<>();

    public LentBookRepository(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        mocking();
    }

    public void mocking(){
        lentBookList.add(new LentBook(bookRepository.getBookList().get(0)));
        lentBookList.add(new LentBook(bookRepository.getBookList().get(1)));
        lentBookList.add(new LentBook(bookRepository.getBookList().get(2)));
    }

}
