package com.switchfully.digibooky.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMemberDto {
    @NotNull
    @NotEmpty
    private  String password;
    @NotNull
    @NotEmpty
    private  String inss;
    @NotNull
    @NotEmpty
    private  String lastname;
    private  String firstname;
    @NotNull
    @NotEmpty
    private  String email;
    private String streetname;
    private int streetNumber;
    @NotNull
    @Min(1000)
    @Max(9999)
    private int postcode;
    @NotNull
    @NotEmpty
    private String city;

    public CreateMemberDto(String password, String inss, String lastname, String firstname, String email, String streetname, int streetNumber, int postcode, String city) {
        this.password = password;
        this.inss = inss;
        this.lastname = lastname;
        setFirstname(firstname);
        this.email = email;
        setStreetname(streetname);
        this.streetNumber = streetNumber;
        this.postcode = postcode;
        this.city = city;
    }

    public void setFirstname(String firstname) {
        if (firstname == null) {
            this.firstname = "";
        } else {
            this.firstname = firstname;
        }
    }

    public void setStreetname(String streetname) {
        if (streetname == null) {
            this.streetname = "";
        } else {
            this.streetname = streetname;
        }
    }
}
