package com.switchfully.digibooky.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Member extends User {

    private final String INSS;
    private final String streetname;
    private final int streetNumber;
    private final int postcode;
    private final String city;

    public Member(String password, String lastname, String firstname, String email, String INSS, String streetname, int streetNumber, int postcode, String city) {
        super(password, lastname, firstname, email, Role.MEMBER);
        this.INSS = INSS;
        this.streetname = streetname;
        this.streetNumber = streetNumber;
        this.postcode = postcode;
        this.city = city;
    }
}
