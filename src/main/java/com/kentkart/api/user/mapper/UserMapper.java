package com.kentkart.api.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kentkart.api.user.User;
import com.kentkart.api.user.dto.SignUpDto;
import com.kentkart.api.user.dto.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
