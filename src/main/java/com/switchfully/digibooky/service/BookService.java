package com.switchfully.digibooky.service;

import com.switchfully.digibooky.exceptions.BookByISBNNotFoundException;
import com.switchfully.digibooky.dto.BookDto;
import com.switchfully.digibooky.mapper.BookMapper;
import com.switchfully.digibooky.models.Book;
import com.switchfully.digibooky.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    public List<BookDto> getAllBooks() {
        return bookMapper.toDto(bookRepository.getBookList());
    }

    public BookDto getBookByISBN(String ISBN) {
        List<Book> bookList = bookRepository.getBookList();
       Book book =  bookList.stream().filter(b -> b.getISBN().equals(ISBN)).findFirst().orElseThrow(() -> new BookByISBNNotFoundException("No book found for given ISBN"));


        return bookMapper.toDto(book);
    }
}
