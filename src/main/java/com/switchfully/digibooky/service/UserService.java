package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.exceptions.InssAlreadyExistsException;
import com.switchfully.digibooky.exceptions.UserAlreadyExistsException;
import com.switchfully.digibooky.mapper.MemberMapper;
import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MemberMapper memberMapper;

    public UserService(UserRepository userRepository, MemberMapper memberMapper) {
        this.userRepository = userRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto createNewMember(MemberDto memberDto) throws UserAlreadyExistsException, InssAlreadyExistsException {
        if (userRepository.emailAlreadyUsed(memberDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.inssAlreadyUsed(memberDto.getInss())) {
            throw new InssAlreadyExistsException();
        }
        userRepository.save(memberMapper.toMember(memberDto));
        return memberMapper.toDto((Member) userRepository.getUser(memberDto.getEmail()));
    }

    public List<MemberDto> getAllMembers() {
        return memberMapper.toDto(userRepository.getAllUsers());
    }
}
