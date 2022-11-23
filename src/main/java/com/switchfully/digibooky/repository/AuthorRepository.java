package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Author;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class AuthorRepository {
    private final List<Author> authorList = new ArrayList<>();

    public AuthorRepository() {
        mocking();
    }

    public void mocking(){
        authorList.add(new Author("Sven","Prof"));
        authorList.add(new Author("Loic","Professor"));
        authorList.add(new Author("Miriam","Prof"));
    }

    public List<Author> getAuthorList() {
        return authorList;
    }
}
