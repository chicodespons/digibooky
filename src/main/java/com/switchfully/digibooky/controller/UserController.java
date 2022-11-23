package com.switchfully.digibooky.controller;


import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.models.Feature;
import com.switchfully.digibooky.models.User;
import com.switchfully.digibooky.security.SecurityService;
import com.switchfully.digibooky.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/user")
public class UserController {

    private final UserService userService;
    private SecurityService securityService;
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberDto> getAllMembers(@RequestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.GET_ALL_MEMBERS);
        return userService.getAllMembers();
    }







//
//    If any other user tries to view all member within the system, the server should respond with 403 Forbidden and a custom message.
//    Hint: provide the unique user identification number as a means of authentication and use it to validate authorization.
//    The INSS should be excluded from the members when returned (sensitive information)




}
