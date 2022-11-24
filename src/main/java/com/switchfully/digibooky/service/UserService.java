package com.switchfully.digibooky.service;

import com.switchfully.digibooky.dto.CreateMemberDto;
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

    public MemberDto createNewMember(CreateMemberDto createMemberDto) throws UserAlreadyExistsException, InssAlreadyExistsException {
        if (userRepository.emailAlreadyUsed(createMemberDto.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        if (userRepository.inssAlreadyUsed(createMemberDto.getInss())) {
            throw new InssAlreadyExistsException();
        }
        userRepository.save(memberMapper.createToMember(createMemberDto));
        return memberMapper.toDto((Member) userRepository.getUser(createMemberDto.getEmail()));
    }

    public List<MemberDto> getAllMembers() {
        return memberMapper.toDto(userRepository.getAllMembers());
    }
}
