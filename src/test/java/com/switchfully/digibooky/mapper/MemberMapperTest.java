package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.dto.CreateMemberDto;
import com.switchfully.digibooky.dto.MemberDto;
import com.switchfully.digibooky.models.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;


    @Test
    @DisplayName("Test given a member return a memberDto")
    void toDto_whenGivenAMember_returnAMemberDto() {
        Member member = new Member("123","Lolita", "Lola", "lolita@email.be", "1245454dd", "stekelstraat", 4, 8400, "StekelCity");
        MemberDto memberDto = new MemberDto("Lolita", "Lola", "lolita@email.be","stekelstraat", 4, 8400, "StekelCity" );

        Assertions.assertEquals(memberDto, memberMapper.toDto(member));
    }

    @Test
    @DisplayName("Test given a collection of members return a list of memberDto's")
    void toDto_whenGivenCollectionOfMembers_returnMemberDtoList() {

        Collection<Member> memberCollection = new ArrayList<>();
        memberCollection
                .add(new Member("123","Lolita", "Lola", "lolita@email.be", "1245454dd", "stekelstraat", 4, 8400, "StekelCity"));
        memberCollection
                .add(new Member("123","Ronny", "Ron", "ron@email.be", "d45d4d5d", "ronnystraat", 5, 8000, "RonCity"));
        memberCollection
                .add(new Member("123","bonny", "Bon", "bon@email.be", "s4s58s4s", "bonnystraat", 6, 7000, "BonCity"));

        List<MemberDto> memberDtoList = new ArrayList<>();
        memberDtoList.add(new MemberDto("Lolita", "Lola", "lolita@email.be","stekelstraat", 4, 8400, "StekelCity" ));
        memberDtoList.add(new MemberDto("Ronny", "Ron", "ron@email.be","ronnystraat", 5, 8000, "RonCity"));
        memberDtoList.add(new MemberDto("bonny", "Bon", "bon@email.be","bonnystraat", 6, 7000, "BonCity"));

        Assertions.assertEquals(memberDtoList, memberMapper.toDto(memberCollection));

    }

    @Test
    @DisplayName("Test given a createToMember return a Member")
    void createToMember_whenGivenACreateToMemberDto_returnAMember() {
        CreateMemberDto createMemberDto = new CreateMemberDto("123","1245454dd", "Lolita", "Lola", "lolita@email.be", "stekelstraat", 4, 8400, "StekelCity");
        Member member = new Member("123","Lolita", "Lola", "lolita@email.be", "1245454dd", "stekelstraat", 4, 8400, "StekelCity");

        Assertions.assertEquals(member, memberMapper.createToMember(createMemberDto));
    }
}