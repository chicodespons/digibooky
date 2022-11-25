package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.InvalidEmailAddressException;
import com.switchfully.digibooky.exceptions.InvalidRoleException;
import com.switchfully.digibooky.mapper.MemberMapper;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.models.Role;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MemberMapper memberMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserService userService;


    @Test
    void creatingMemberWithInvalidEmailFormat_ShouldReturn_CustomExceptionWithMessage() {
        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@email.", "Stationstraat",  10, 1000, "Brussel");
        CreateMemberDto member1 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@.com", "Stationstraat",  10, 1000, "Brussel");
        CreateMemberDto member2 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "@email.com", "Stationstraat",  10, 1000, "Brussel");

        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewMember(member);});
        assertTrue(exception.getMessage().contains("Invalid email format. Email must be of format X@X.X"));

        Exception exception1 = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewMember(member1);});
        assertTrue(exception1.getMessage().contains("Invalid email format. Email must be of format X@X.X"));

        Exception exception2 = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewMember(member2);});
        assertTrue(exception2.getMessage().contains("Invalid email format. Email must be of format X@X.X"));
    }

    @Test
    void creatingMemberWithAlreadyRegisteredEmail_ShouldReturn_CustomExceptionWithMessage() {
        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@thisEmailShouldOnlyBeUsedForTests.com", "Stationstraat",  10, 1000, "Brussel");
        CreateMemberDto member2 = new CreateMemberDto("a", "123asssssa", "Deketelaere", "Loïc", "loic@thisEmailShouldOnlyBeUsedForTests.com", "Stationstraat",  10, 1000, "Brussel");

        // below is executed so that we are sure we registered the used email address at least once
        try {
            userService.createNewMember(member2);
        } catch (InvalidEmailAddressException e) {
            throw new RuntimeException(e);
        } catch (InssAlreadyExistsException e) {
            throw new RuntimeException(e);
        }

        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewMember(member);});
        assertTrue(exception.getMessage().contains("The email address you provided is already in use."));
    }

    @Test
    void creatingMemberWithAlreadyRegisteredInss_ShouldReturn_CustomExceptionWithMessage() {
        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@thisEmailgggShouldOnlyBeUsedForTests.com", "Stationstraat",  10, 1000, "Brussel");
        CreateMemberDto member2 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@mailShouldOfffffffffnlyBeUsedForTests.com", "Stationstraat",  10, 1000, "Brussel");

        // below is executed so that we are sure we registered the used email address at least once
        try {
            userService.createNewMember(member2);
        } catch (InvalidEmailAddressException e) {
            throw new RuntimeException(e);
        } catch (InssAlreadyExistsException e) {
            throw new RuntimeException(e);
        }

        Exception exception = assertThrows(InssAlreadyExistsException.class, () -> {userService.createNewMember(member);});
        assertTrue(exception.getMessage().contains("The INSS you provided has already been used."));
    }

    @Test
    void creatingMemberWithValidData_ShouldReturn_NewlyCreatedMember() throws InssAlreadyExistsException, InvalidEmailAddressException {
        CreateMemberDto member = new CreateMemberDto("a", "123ajja", "Deketelaere", "Loïc", "loic@thisEmailShoujk;l;ldOnlyBeUsedForTests.com", "Stationstraat",  10, 1000, "Brussel");

        MemberDto createdMember = userService.createNewMember(member);

        assertTrue(createdMember.getEmail().equals("loic@thisEmailShoujk;l;ldOnlyBeUsedForTests.com"));
    }

    @Test
    void creatingUserWithValidData_ShouldReturn_NewlyCreatedUser() throws InvalidRoleException, InvalidEmailAddressException {
        CreateUserDto librarian = new CreateUserDto("a", "dek", "Loïc", "el@dffffffffffffffffffek.com", "LIBRARIAN");
        CreateUserDto admin = new CreateUserDto("a", "dek", "Loïc", "el@dedfffffffffdk.com", "admin");

        UserDto returnedLibrarian = userService.createNewUser(librarian);
        UserDto returnedAdmin = userService.createNewUser(admin);

        assertTrue(returnedLibrarian.getRole().equals(Role.LIBRARIAN));
        assertTrue(returnedAdmin.getRole().equals(Role.ADMIN));
    }

    @Test
    void creatingUserWithAlreadyRegisteredEmail_ShouldReturn_CustomExceptionWithMessage() throws InvalidRoleException, InvalidEmailAddressException {
        CreateUserDto librarian = new CreateUserDto("a", "dek", "Loïc", "el@dek.com", "LIBRARIAN");
        CreateUserDto librarian1 = new CreateUserDto("a", "dek", "Loïc", "el@dek.com", "LIBRARIAN");

        userService.createNewUser(librarian);
        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {userService.createNewUser(librarian1);});

        assertTrue(exception.getMessage().contains("The email address you provided is already in use."));
    }


}