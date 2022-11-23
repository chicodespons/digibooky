package com.switchfully.digibooky.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class MemberDto {

    private  String password;
    private  String lastname;
    private  String firstname;
    private  String email;
    private String streetname;
    private int streetNumber;
    private int postcode;
    private String city;

    public MemberDto(String password, String lastname, String firstname, String email, String streetname, int streetNumber, int postcode, String city) {
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.streetname = streetname;
        this.streetNumber = streetNumber;
        this.postcode = postcode;
        this.city = city;
    }
}
