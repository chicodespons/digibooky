package com.switchfully.digibooky.models;

public enum Role {
    MEMBER(new ArrayList<>(List.of(Feature.GET_ALL_BOOKS, Feature.GET_BOOK_BY_ID))),
    ADMIN(new ArrayList<>(List.of(Feature.ALL))),
    LIBRARIAN(new ArrayList<>(List.of(Feature.ALL)));
    // add authorization tussen haakjes wanneer nodig, add dan ook een getter
    // and then you keep adding hé;
    private List<Feature> featureList;

    Role(List<Feature> featureList) {
        this.featureList = featureList;
    }


}
