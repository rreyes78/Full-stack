package com.rm.jwt.backend.mapper;

import com.rm.jwt.backend.dto.SignUpDto;
import com.rm.jwt.backend.dto.UserDto;
import com.rm.jwt.backend.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDtoUser(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUpDto);

}
