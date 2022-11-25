package com.switchfully.digibooky.models;

import java.util.ArrayList;
import java.util.List;

import static com.switchfully.digibooky.models.Feature.*;

public enum Role {
    MEMBER(new ArrayList<>(List.of(GET_ALL_BOOKS, GET_BOOK_BY_ID, GET_BOOK_BY_ISBN, GET_BOOK_BY_TITLE, GET_BOOK_BY_AUTHOR, RETURN_BOOK))),
    ADMIN(new ArrayList<>(List.of(GET_ALL_BOOKS,  GET_ALL_MEMBERS, GET_BOOK_BY_ISBN, GET_BOOK_BY_TITLE, GET_BOOK_BY_AUTHOR, LIBRARIAN_REGISTRATION, RETURN_BOOK))),
    LIBRARIAN(new ArrayList<>(List.of(GET_ALL_BOOKS, GET_BOOK_BY_ISBN, UPDATE_BOOK, REGISTER_BOOK,GET_BOOK_BY_TITLE, DELETE_BOOK, GET_BOOK_BY_AUTHOR, RETURN_BOOK)));
    // add authorization tussen haakjes wanneer nodig, add dan ook een getter
    // and then you keep adding hé;
    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }

    public boolean containsFeature(Feature feature) {
        return featureList.contains(feature);
    }

}
