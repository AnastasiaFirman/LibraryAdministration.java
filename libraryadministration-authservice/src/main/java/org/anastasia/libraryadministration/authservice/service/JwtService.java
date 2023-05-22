package org.anastasia.libraryadministration.authservice.service;

import org.anastasia.libraryadministration.authservice.dto.UserDto;

public interface JwtService {
    String getJwt(UserDto userDto);
}
