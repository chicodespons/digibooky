package com.switchfully.digibooky.models.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AuthorDto {

    private String lastname;
    private String firstname;

    public AuthorDto(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
