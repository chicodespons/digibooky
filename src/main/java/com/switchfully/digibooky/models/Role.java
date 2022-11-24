package com.switchfully.digibooky.models;

import java.util.ArrayList;
import java.util.List;

import static com.switchfully.digibooky.models.Feature.*;

public enum Role {
    MEMBER(new ArrayList<>(List.of(GET_ALL_BOOKS, GET_BOOK_BY_ID))),
    ADMIN(new ArrayList<>(List.of(GET_ALL_BOOKS,  GET_ALL_MEMBERS, GET_BOOK_BY_ISBN, LIBRARIAN_REGISTRATION))),
    LIBRARIAN(new ArrayList<>(List.of(GET_ALL_BOOKS, GET_BOOK_BY_ISBN, UPDATE_BOOK, REGISTER_BOOK,GET_BOOK_BY_TITLE)));
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
