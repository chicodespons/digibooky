package com.switchfully.digibooky.security;

import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecurityServiceTest {

    @Autowired
    private SecurityService securityService;

    @Test
    @DisplayName("Testing validation to not throw when correct email and password")
    void givenCorrectEmailAndPaswor_whenValidateAuthorization_thenNoErrorCanBeThrown(){
        //given
        String correctInput = "sven@mail.be:azerty";
        byte[] inputEncoded = Base64.getEncoder().encode(correctInput.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //
        Feature feature = Feature.GET_ALL_BOOKS;

        //then
        Assertions.assertDoesNotThrow(() -> securityService.validateAuthorization(authorizationString, feature));
    }

    @Test
    @DisplayName("Testing validation to throw error when email is not in userlist")
    void givenEmailThatIsNotKnow_whenValidateAuthorization_thenErrorShouldBeThrown(){
        //given
        String incorrectEmail = "unkown@mail.be:azerty";
        byte[] inputEncoded = Base64.getEncoder().encode(incorrectEmail.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
       //
        Feature feature = Feature.GET_ALL_BOOKS;
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> securityService.validateAuthorization(authorizationString,feature));
    }

    @Test
    @DisplayName("Testing validation to throw error when email correct but password is incorrect")
    void givenEmailThatIsKnowButWrongPasword_whenValidateAuthorization_thenErrorMessageShouldBeThrown(){
        //given
        String wrongPasword = "sven@mail.be:wrongPasword";
        byte[] inputEncoded = Base64.getEncoder().encode(wrongPasword.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);

        Feature feature = Feature.GET_ALL_BOOKS;
        //when

        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> securityService.validateAuthorization(authorizationString,feature));
    }

    @Test
    @DisplayName("Testing validation to throw correct error message when User has no acces to Feature")
    void givenAMember_whenAccessingFeatureThatIsNotAllowed_thenErrorMessageShouldBeThrown(){
        //given
        String correctMember = "testMember@mail.com:testMember";
        byte[] inputEncoded = Base64.getEncoder().encode(correctMember.getBytes());
        String authorizationString = "Basic " + new String(inputEncoded);
        //
        Feature feature = Feature.TEST_FEATURE;
        //when
        Throwable ex = catchThrowable(()->securityService.validateAuthorization(authorizationString,feature));
        //then
        Assertions.assertEquals("User does not have access.",ex.getMessage() );
    }
}