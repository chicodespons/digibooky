package com.switchfully.digibooky.models;

import java.util.ArrayList;
import java.util.List;

public enum Role {
    MEMBER(new ArrayList<>(List.of(Feature.GET_ALL_BOOKS, Feature.GET_BOOK_BY_ID))),
    ADMIN(new ArrayList<>(List.of(Feature.GET_ALL_BOOKS,  Feature.GET_ALL_MEMBERS, Feature.GET_BOOK_BY_ISBN))),
    LIBRARIAN(new ArrayList<>(List.of(Feature.GET_ALL_BOOKS, Feature.GET_BOOK_BY_ISBN, Feature.UPDATE_BOOK,  Feature.REGISTER_BOOK)));
    // add authorization tussen haakjes wanneer nodig, add dan ook een getter
    // and then you keep adding hé;
    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }
    public boolean containsFeature(Feature feature){
        return featureList.contains(feature);
    }

}
