package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.LentBook;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ShowMemberDto {

    private  String lastname;
    private  String firstname;
    private  String email;
    private LentBook lentBook;

    public ShowMemberDto(String lastname, String firstname, String email, LentBook lentBook) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.lentBook = lentBook;

    }


}
