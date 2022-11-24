package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.models.Member;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class MemberMapper {
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getPassword(), member.getLastname(), member.getFirstname(),
                member.getEmail(), member.getStreetname(), member.getStreetNumber(),
                member.getPostcode(), member.getCity());
    }

    public List<MemberDto> toDto(Collection<Member> memberCollection) {
        return memberCollection.stream()
                .map(member -> toDto(member))
                .toList();
    }

    public Member createToMember(CreateMemberDto createMemberDto) {
        return new Member(createMemberDto.getPassword(), createMemberDto.getLastname(), createMemberDto.getFirstname(), createMemberDto.getEmail(),
                createMemberDto.getInss(), createMemberDto.getStreetname(), createMemberDto.getStreetNumber(), createMemberDto.getPostcode(), createMemberDto.getCity());
    }
}
