package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.exceptions.UserCreationException;
import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.InvalidEmailAddressException;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.service.UserService;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    //Get all members   http://localhost:8080/users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDto> getAllMembers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_MEMBERS);
        return userService.getAllMembers();
    }

    //Register a new members   http://localhost:8080/users/new/member
    @PostMapping(path = "/new/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public MemberDto registerNewMember(@Valid @RequestBody CreateMemberDto createMemberDto, BindingResult errors) throws InssAlreadyExistsException, InvalidEmailAddressException, UserCreationException {
        if (errors.hasErrors()) {
            throw new UserCreationException();
        }
        return userService.createNewMember(createMemberDto);
    }

    //Register a new user   http://localhost:8080/users/new
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDto registerNewUser(@Valid @RequestBody CreateUserDto createUserDto,
                                   @RequestHeader String authorization, BindingResult errors) throws UserCreationException, InvalidEmailAddressException {
        if (errors.hasErrors()) {
            throw new UserCreationException();
        }
        securityService.validateAuthorization(authorization, Feature.LIBRARIAN_REGISTRATION);
        return userService.createNewUser(createUserDto);
    }
}
