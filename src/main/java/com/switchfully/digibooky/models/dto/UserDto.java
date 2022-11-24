package com.switchfully.digibooky.models.dto;

import com.switchfully.digibooky.models.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDto {
    @NotNull
    @NotEmpty
    private final String password;
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

    public UserDto(String password, String lastname, String firstname, String email, Role role) {
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = role;
    }
}
