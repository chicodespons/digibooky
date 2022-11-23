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
public class UserDto {
    private final String password;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final Role role;

    public UserDto(String password, String lastname, String firstname, String email, Role role) {
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = role;
    }
}
