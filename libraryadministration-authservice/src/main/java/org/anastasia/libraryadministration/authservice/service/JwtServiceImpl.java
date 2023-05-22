package org.anastasia.libraryadministration.authservice.service;

import lombok.RequiredArgsConstructor;
import org.anastasia.libraryadministration.authservice.component.JwtBuilder;
import org.anastasia.libraryadministration.authservice.dto.UserDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtServiceImpl implements JwtService{
    private final AuthenticationManager authenticationManager;
    private final JwtBuilder jwtBuilder;

    @Override
    //TODO проверить что возвращает authenticate
    public String getJwt(UserDto userDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        return jwtBuilder.buildJwt((UserDetails) authenticate.getPrincipal());
    }
}