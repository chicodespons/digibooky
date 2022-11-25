package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.exceptions.IncorrectLogInInformationException;
import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.models.Role;
import com.switchfully.digibooky.models.User;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    @Test
    @DisplayName("When asking to see all members as a member, exception should be thrown")
    void whenAskingToSeeAllMembersAsAMember_shouldNotBeAllowed(){
        //given
        Member member = new Member("12315615","BookController1","Sven","UserControllerTest1@mail.be","1591","teststraat",156, 156, "Brussel");
        userRepository.addMember(member);

        String correctEmail = "UserControllerTest1@mail.be:12315615";
        byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //when
        Assertions.assertThrows(IncorrectLogInInformationException.class, ()-> userController.getAllMembers(authorizationString));
    }
    @Test
    @DisplayName("When asking to see all members as a an Admin, no exception should be thrown")
    void whenAskingToSeeAllMembersAsAnAdmin_shouldBeAllowed(){
        //given
        User admin = new User("12315615","BookController2","Sven","UserControllerTest2@mail.be", Role.ADMIN);
        userRepository.addUser(admin);

        String correctEmail = "UserControllerTest2@mail.be:12315615";
        byte[] inputEncoded = Base64.getEncoder().encode(correctEmail.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //when
        Assertions.assertDoesNotThrow(()-> userController.getAllMembers(authorizationString));
    }


}