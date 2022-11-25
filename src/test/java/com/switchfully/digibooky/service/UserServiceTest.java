package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.InvalidEmailAddressException;
import com.switchfully.digibooky.mapper.MemberMapper;
import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {

    @Test
    void creatingMemberWithInvalidEmailFormat_ShouldReturn_CustomExceptionWithMessage() {
        UserRepository userRepository = new UserRepository();
        MemberMapper memberMapper = new MemberMapper();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository, memberMapper, userMapper);

        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@email.", "Stationstraat", 10, 1000, "Brussel");
        CreateMemberDto member1 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@.com", "Stationstraat", 10, 1000, "Brussel");
        CreateMemberDto member2 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "@email.com", "Stationstraat", 10, 1000, "Brussel");

        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {
            userService.createNewMember(member);
        });
        assertTrue(exception.getMessage().contains("Invalid email format. Email must be of format X@X.X"));

        Exception exception1 = assertThrows(InvalidEmailAddressException.class, () -> {
            userService.createNewMember(member1);
        });
        assertTrue(exception1.getMessage().contains("Invalid email format. Email must be of format X@X.X"));

        Exception exception2 = assertThrows(InvalidEmailAddressException.class, () -> {
            userService.createNewMember(member2);
        });
        assertTrue(exception2.getMessage().contains("Invalid email format. Email must be of format X@X.X"));
    }

    @Test
    void creatingMemberWithAlreadyRegisteredEmail_ShouldReturn_CustomExceptionWithMessage() {
        UserRepository userRepository = new UserRepository();
        MemberMapper memberMapper = new MemberMapper();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository, memberMapper, userMapper);

        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@thisEmailShouldOnlyBeUsedForTests.com", "Stationstraat", 10, 1000, "Brussel");
        CreateMemberDto member2 = new CreateMemberDto("a", "123asssssa", "Deketelaere", "Loïc", "loic@thisEmailShouldOnlyBeUsedForTests.com", "Stationstraat", 10, 1000, "Brussel");

        // below is executed so that we are sure we registered the used email address at least once
        try {
            userService.createNewMember(member2);
        } catch (InvalidEmailAddressException e) {
            throw new RuntimeException(e);
        } catch (InssAlreadyExistsException e) {
            throw new RuntimeException(e);
        }

        Exception exception = assertThrows(InvalidEmailAddressException.class, () -> {
            userService.createNewMember(member);
        });
        assertTrue(exception.getMessage().contains("The email address you provided is already in use."));
    }

    @Test
    void creatingMemberWithAlreadyRegisteredInss_ShouldReturn_CustomExceptionWithMessage() {
        UserRepository userRepository = new UserRepository();
        MemberMapper memberMapper = new MemberMapper();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository, memberMapper, userMapper);

        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@thisEmailShouldOnlyBeUsedForTests.com", "Stationstraat", 10, 1000, "Brussel");
        CreateMemberDto member2 = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@mailShouldOnlyBeUsedForTests.com", "Stationstraat", 10, 1000, "Brussel");

        // below is executed so that we are sure we registered the used email address at least once
        try {
            userService.createNewMember(member2);
        } catch (InvalidEmailAddressException e) {
            throw new RuntimeException(e);
        } catch (InssAlreadyExistsException e) {
            throw new RuntimeException(e);
        }

        Exception exception = assertThrows(InssAlreadyExistsException.class, () -> {
            userService.createNewMember(member);
        });
        assertTrue(exception.getMessage().contains("The INSS you provided has already been used."));
    }

    @Test
    void creatingMemberWithValidData_ShouldReturn_NewlyCreatedMember() throws InssAlreadyExistsException, InvalidEmailAddressException {
        UserRepository userRepository = new UserRepository();
        MemberMapper memberMapper = new MemberMapper();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository, memberMapper, userMapper);

        CreateMemberDto member = new CreateMemberDto("a", "123aa", "Deketelaere", "Loïc", "loic@thisEmailShouldOnlyBeUsedForTests.com", "Stationstraat", 10, 1000, "Brussel");

        MemberDto createdMember = userService.createNewMember(member);

        assertTrue(createdMember.getEmail().equals("loic@thisEmailShouldOnlyBeUsedForTests.com"));
    }

    @Test
    @DisplayName("When calling all members as an admin, we should get a list that contains all member")
    void whenCallingAllMemberAsAdmin_listWithAllMemberShouldBeGiven() {
        UserRepository userRepository = new UserRepository();
        MemberMapper memberMapper = new MemberMapper();
        UserMapper userMapper = new UserMapper();
        UserService userService = new UserService(userRepository, memberMapper, userMapper);

        //given
        Member memberOne = new Member("badPassword", "Boeckstaens", "Sven", "sven@mail.com", "123.549.465", "Nieuwbaan", 45, 1501, "Ternat");
        userRepository.addMember(memberOne.getEmail(), memberOne);
        //when
        List<MemberDto> memberDtoList = userService.getAllMembers();
        //
        Assertions.assertTrue(memberDtoList.contains(memberMapper.toDto(memberOne)));
    }
}