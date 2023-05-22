package org.anastasia.libraryadministration.authservice.service;

import org.anastasia.libraryadministration.authservice.BaseIntegrationTest;
import org.anastasia.libraryadministration.authservice.TestDataProvider;
import org.anastasia.libraryadministration.authservice.model.User;
import org.anastasia.libraryadministration.authservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

class SignUpServiceTest extends BaseIntegrationTest {
    private final SignUpService signUpService;
    private final UserRepository userRepository;

    @Autowired
    SignUpServiceTest(SignUpService signUpService, UserRepository userRepository) {
        this.signUpService = signUpService;
        this.userRepository = userRepository;
    }

    @Test
    void signUpTest() {
        User user = TestDataProvider.buildUser(1);
        signUpService.signUp(user);
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        Assertions.assertEquals(user.getUsername(), foundUser.get().getUsername());

    }

}