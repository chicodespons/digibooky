package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.models.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDto {
    @NotNull
    @NotEmpty
    private final String lastname;
    @NotNull
    @NotEmpty
    private final String firstname;
    @NotNull
    @NotEmpty
    private final String email;
    @NotNull
    @NotEmpty
    private final Role role;

    public UserDto(String lastname, String firstname, String email, Role role) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = role;
    }
}
