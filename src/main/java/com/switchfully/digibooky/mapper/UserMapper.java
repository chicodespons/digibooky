package com.switchfully.digibooky.mapper;

import com.switchfully.digibooky.models.Member;
import com.switchfully.digibooky.models.User;
import com.switchfully.digibooky.models.dto.CreateMemberDto;
import com.switchfully.digibooky.models.dto.CreateUserDto;
import com.switchfully.digibooky.models.dto.MemberDto;
import com.switchfully.digibooky.models.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return new UserDto(user.getPassword(), user.getLastname(), user.getFirstname(),
                user.getEmail(), user.getRole());
    }

    public List<UserDto> toDto(Collection<User> userCollection) {
        return userCollection.stream()
                .map(user -> toDto(user))
                .toList();
    }

    public User createToUser(CreateUserDto createUserDto) {
        return new User(createUserDto.getPassword(), createUserDto.getLastname(), createUserDto.getFirstname(),
                createUserDto.getEmail(), createUserDto.getRole());
    }

    public User toUser(UserDto userDto) {
        return new User(userDto.getPassword(), userDto.getLastname(), userDto.getFirstname(), userDto.getEmail(), userDto.getRole());
    }
}
