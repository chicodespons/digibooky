package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.exceptions.InvalidRoleException;
import com.switchfully.digibooky.models.Role;
import com.switchfully.digibooky.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    @DisplayName("Test when given a user than return userDto")
    void toDto_whenGivenUser_returnAUserDto() {
        User user = new User("abc","Lastname", "Firstname", "email@email.be", Role.MEMBER);
        UserDto userDto = new UserDto("Lastname", "Firstname", "email@email.be", Role.MEMBER);

        Assertions.assertEquals(userDto, userMapper.toDto(user));
    }

    @Test
    @DisplayName("Test when given a collection of users return list of userDto's")
    void testToDto_whenGivenAUserCollection_returnListOfUserDto() {
        Collection<User> userCollection = new ArrayList<>();
        userCollection.add(new User("abc","Lastname", "Firstname", "email@email.be", Role.MEMBER));
        userCollection.add(new User("ddd","Pastname", "Pirstname", "Pmail@email.be", Role.ADMIN));
        userCollection.add(new User("sss","Fastname", "Lirstname", "mail@email.be", Role.LIBRARIAN));
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto("Lastname", "Firstname", "email@email.be", Role.MEMBER));
        userDtos.add(new UserDto("Pastname", "Pirstname", "Pmail@email.be", Role.ADMIN));
        userDtos.add(new UserDto("Fastname", "Lirstname", "mail@email.be", Role.LIBRARIAN));

        Assertions.assertEquals(userDtos, userMapper.toDto(userCollection));
    }

    @Test
    @DisplayName("Test when given a createUserDto return a User")
    void createToUser_whenGivenACreateUserDto_returnAUser() throws InvalidRoleException {

        CreateUserDto createUserDto = new CreateUserDto("abc","Lastname", "Firstname", "email@email.be", "MEMBER");
        User user = new User("abc","Lastname", "Firstname", "email@email.be", Role.MEMBER);

        //because email is unique in our userrepository
        //can't compare objects because id is randomly assigned
        Assertions.assertEquals(user.getEmail(), userMapper.createToUser(createUserDto).getEmail());


    }

}