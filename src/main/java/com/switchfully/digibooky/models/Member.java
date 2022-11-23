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
    private final String postcode;
    private final String city;

    public Member(String password, String userId, String lastname, String firstname, String email, Role role, String INSS, String streetname, String postcode, String city) {
        super(password, userId, lastname, firstname, email, role);
        this.INSS = INSS;
        this.streetname = streetname;
        this.postcode = postcode;
        this.city = city;
    }

}
