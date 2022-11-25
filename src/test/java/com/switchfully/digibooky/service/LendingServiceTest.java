package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.exceptions.InvalidEmailAddressException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LendingServiceTest {
    @Autowired
    LendingService lendingService;

//    @Test
//    void LendingBookWithWrongLogin_ShouldReturn_IncorrectLogInInformationException() {
//        String authorization = new String(Base64.getEncoder().encode("loic@email.com:a"));
//        CreateMemberDto member2 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "@email.com", "Stationstraat",  10, 1000, "Brussel");
//
//        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewMember(member);});
//        assertTrue(exception.getMessage().contains("Invalid email format. Email must be of format X@X.X"));
//    }
}