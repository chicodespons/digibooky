package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<MemberDto> getAllMembers() {
        return getAllMembers();
    }

}
