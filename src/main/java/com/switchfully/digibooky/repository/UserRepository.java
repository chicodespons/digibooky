package com.switchfully.digibooky.repository;

import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.models.Role;
import com.switchfully.digibooky.models.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public UserRepository() {
        mockData();
    }

    public User getUser(String email) {
        return userMap.get(email);
    }

    public Optional<User> getUserById(String userId) {
        return userMap.values().stream()
                .filter(user -> user.getUserId().equals(userId))
                .findFirst();
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user instanceof Member) {
                members.add((Member) user);
            }
        }
        return members;
    }

    public boolean inssAlreadyUsed(String inss) {
        List<Member> memberList = getAllMembers();
        for (Member member : memberList) {
            if (member.getINSS().equals(inss)) {
                return true;
            }
        }
        return false;
    }

    public boolean emailAlreadyUsed(String email) {
        return userMap.containsKey(email);
    }

    public void save(User user) {
        userMap.put(user.getEmail(), user);
    }

    private void mockData() {
        User member = new Member("a", "Deketelaer", "Loïc", "loic@email.com", "89.12-5", "Stationstraat", 10, 1000, "Brussel");
        User admin = new User("b", "De Beste", "Pieter", "pieter@mail.be", Role.ADMIN);
        User librarian = new User("azerty", "Boeckstaens", "Sven", "sven@mail.be", Role.LIBRARIAN);
        User memberForDemo = new Member("a", "Member", "Demo", "member@email.com", "1111", "memberstreet", 2, 1000, "MemberCity");
        User adminDemo = new User("a", "Admin", "Demo", "admin@email.com", Role.ADMIN);
        User librarianDemo = new User("a", "Librarian", "Demo", "librarian@email.com", Role.LIBRARIAN);

        userMap.put(member.getEmail(), member);
        userMap.put(admin.getEmail(), admin);
        userMap.put(librarian.getEmail(), librarian);
        userMap.put(memberForDemo.getEmail(), memberForDemo);
        userMap.put(adminDemo.getEmail(), adminDemo);
        userMap.put(librarianDemo.getEmail(), librarianDemo);
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(userMap.values());
        return users;
    }

    public void addMember(Member member) {
        userMap.put(member.getEmail(), member);
    }

    public void addUser(User user) {
        userMap.put(user.getEmail(), user);
    }
}
