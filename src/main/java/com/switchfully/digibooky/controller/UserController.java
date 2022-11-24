package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.UserAlreadyExistsException;
import com.switchfully.digibooky.repository.UserRepository;
import com.switchfully.digibooky.service.UserService;
import org.springframework.http.MediaType;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.security.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final SecurityService securityService;

    public UserController(UserService userService, SecurityService securityService, UserRepository userRepository) {
        this.userService = userService;
        this.securityService = securityService;
        this.userRepository = userRepository;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDto> getAllMembers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_MEMBERS);
        return userService.getAllMembers();
    }

    @PostMapping(path = "new/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDto registerNewMember(@RequestBody CreateMemberDto createMemberDto) throws InssAlreadyExistsException, UserAlreadyExistsException {
        return userService.createNewMember(createMemberDto);
    }
}
