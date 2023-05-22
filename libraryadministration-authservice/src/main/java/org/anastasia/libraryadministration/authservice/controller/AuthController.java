package org.anastasia.libraryadministration.authservice.controller;

import lombok.RequiredArgsConstructor;
import org.anastasia.libraryadministration.authservice.dto.UserDto;
import org.anastasia.libraryadministration.authservice.mappers.UserMapper;
import org.anastasia.libraryadministration.authservice.service.JwtService;
import org.anastasia.libraryadministration.authservice.service.SignUpService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final SignUpService signUpService;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @PostMapping("/api/v1/signup")
    public void signUp(@RequestBody UserDto userDto) {
        signUpService.signUp(userMapper.toEntity(userDto));
    }
    @GetMapping("/api/v1/jwt")
    public String getJwt(@RequestBody UserDto userDto) {
        return jwtService.getJwt(userDto);
    }
}