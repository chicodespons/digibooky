package com.switchfully.digibooky.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberDto {
    private  String password;
    private  String inss;
    private  String lastname;
    private  String firstname;
    private  String email;
    private String streetname;
    private int streetNumber;
    private int postcode;
    private String city;

    public CreateMemberDto(String password, String inss, String lastname, String firstname, String email, String streetname, int streetNumber, int postcode, String city) {
        this.password = password;
        this.inss = inss;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.streetname = streetname;
        this.streetNumber = streetNumber;
        this.postcode = postcode;
        this.city = city;
    }
}
