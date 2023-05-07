package org.anastasia.peopleinfoapplication.service;

import org.anastasia.peopleinfoapplication.dao.BaseIntegrationTest;
import org.anastasia.peopleinfoapplication.model.User;
import org.anastasia.peopleinfoapplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

class UserServiceTest extends BaseIntegrationTest {
    private final UserService userService;
    private final UserRepository userRepository;
    @Autowired
    UserServiceTest(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }
    @Test
    void save() {
        User user = new User("Nastya", "password", true, true, true, true, new HashSet<>());
        userRepository.save(user);

    }
}