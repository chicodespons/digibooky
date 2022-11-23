package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.models.Role;
import com.switchfully.digibooky.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public UserRepository() {
        mockData();
    }

    public User getUser(String email) {
        return userMap.get(email);
    }

    private void mockData() {
        User member = new Member("a", "Deketelaer", "Loïc", "loic@email.com", "89.12-5", "Stationstraat", 10, 1000, "Brussel");
        User admin = new User("b", "De Beste", "Pieter", "pieter@mail.be", Role.ADMIN);
        User librarian = new User("b",  "Boeckstaens", "Sven","sven@mail.be", Role.LIBRARIAN );
        userMap.put(member.getEmail(), member);
        userMap.put(admin.getEmail(), admin);
        userMap.put(librarian.getEmail(), librarian);
    }
}
