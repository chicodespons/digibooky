package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.Role;
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
    private  String userId;
    private  String lastname;
    private  String firstname;
    private  String email;
    private Role role;
    private String streetname;
    private int streetNumber;
    private String postcode;
    private String city;

    public MemberDto(String password, String userId, String lastname, String firstname, String email, Role role, String streetname, int streetNumber, String postcode, String city) {
        this.password = password;
        this.userId = userId;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = role;
        this.streetname = streetname;
        this.streetNumber = streetNumber;
        this.postcode = postcode;
        this.city = city;
    }
}
