package com.switchfully.digibooky.models;

import java.util.Objects;
import java.util.UUID;

public class User {

    private final String password;
    private final String userId;
    private final String lastname;
    private final String firstname;
    private final String email;
    private final Role role;

    public User(String password, String lastname, String firstname, String email, Role role) {
        this.password = password;
        this.userId = UUID.randomUUID().toString();
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.role = role;
    }



    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(password, user.password) && Objects.equals(userId, user.userId) && Objects.equals(lastname, user.lastname) && Objects.equals(firstname, user.firstname) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, userId, lastname, firstname, email, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }


}
