package org.anastasia.libraryadministration.authservice.mappers;

import org.anastasia.libraryadministration.authservice.dto.UserDto;
import org.anastasia.libraryadministration.authservice.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
@Mapper
@Component
public interface UserMapper {
    UserDto toUserDto (User user);
    User toEntity (UserDto userDto);
}
