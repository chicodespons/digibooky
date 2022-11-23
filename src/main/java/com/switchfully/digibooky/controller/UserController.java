package com.switchfully.digibooky.controller;

import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.UserAlreadyExistsException;
import com.switchfully.digibooky.repository.UserRepository;
import com.switchfully.digibooky.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MemberDto> getAllUsers() {
        return userService.getAllMembers();
    }

    @PostMapping(path = "new/member", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MemberDto registerNewMember(@RequestBody CreateMemberDto createMemberDto) throws InssAlreadyExistsException, UserAlreadyExistsException {
        return userService.createNewMember(createMemberDto);
    }
}
