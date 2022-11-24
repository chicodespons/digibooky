package com.switchfully.digibooky.service;

import com.switchfully.digibooky.mapper.UserMapper;
import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.dto.CreateUserDto;
import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.InvalidEmailAddressException;
import com.switchfully.digibooky.mapper.MemberMapper;
import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.dto.UserDto;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MemberMapper memberMapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, MemberMapper memberMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.memberMapper = memberMapper;
        this.userMapper = userMapper;
    }

    public List<MemberDto> getAllMembers() {
        return memberMapper.toDto(userRepository.getAllMembers());
    }

    public MemberDto createNewMember(CreateMemberDto createMemberDto) throws InvalidEmailAddressException, InssAlreadyExistsException {
        validateEmail(createMemberDto.getEmail());
        if (userRepository.inssAlreadyUsed(createMemberDto.getInss())) {
            throw new InssAlreadyExistsException();
        }
        userRepository.save(memberMapper.createToMember(createMemberDto));
        return memberMapper.toDto((Member) userRepository.getUser(createMemberDto.getEmail()));
    }

    public UserDto createNewUser(CreateUserDto createUserDto) throws InvalidEmailAddressException {
        validateEmail(createUserDto.getEmail());
        userRepository.save(userMapper.createToUser(createUserDto));
        return userMapper.toDto(userRepository.getUser(createUserDto.getEmail()));
    }

    private void validateEmail(String email) throws InvalidEmailAddressException {
        if (userRepository.emailAlreadyUsed(email)) {
            throw new InvalidEmailAddressException();
        }
        if (!emailIsValid(email)) {
            throw new InvalidEmailAddressException("Invalid email format. Email must be of format X@X.X");
        }
    }

    private boolean emailIsValid(String email) {
        Pattern pattern = Pattern.compile("(.+)@(.+)\\.(.+)$");
        Matcher emailToValidate = pattern.matcher(email);
        return emailToValidate.matches();
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toDto(userRepository.getAllUsers());
    }
}
