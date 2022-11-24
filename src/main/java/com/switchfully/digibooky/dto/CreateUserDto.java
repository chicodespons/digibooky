package com.switchfully.digibooky.dto;

import com.switchfully.digibooky.exceptions.InvalidRoleException;
import com.switchfully.digibooky.models.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateUserDto {
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
    private Role role;

    public CreateUserDto(String password, String lastname, String firstname, String email, String role) throws InvalidRoleException {
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        setRole(role);
    }

    public void setRole(String roleToValidate) throws InvalidRoleException {
        Role[] roles = Role.values();
        for (Role role : roles) {
            if (role.toString().equalsIgnoreCase(roleToValidate)) {
                this.role = role;
                return;
            }
        }
        throw new InvalidRoleException();
    }
}
