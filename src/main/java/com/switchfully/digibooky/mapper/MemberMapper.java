package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.models.Member;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class MemberMapper {
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getPassword(), member.getUserId(), member.getLastname(), member.getFirstname(),
                member.getEmail(), member.getStreetname(), member.getStreetNumber(),
                member.getPostcode(), member.getCity());
    }

    public List<MemberDto> toDto(Collection<Member> memberCollection) {
        return memberCollection.stream()
                .map(member -> toDto(member))
                .toList();
    }

    public Member toMember(MemberDto memberDto) {
        return new Member(memberDto.getPassword(), memberDto.getLastname(), memberDto.getFirstname(), memberDto.getEmail(),
                memberDto.getInss(), memberDto.getStreetname(), memberDto.getStreetNumber(), memberDto.getPostcode(), memberDto.getCity());
    }
}
