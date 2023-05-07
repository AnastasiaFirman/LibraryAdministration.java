package org.anastasia.peopleinfoapplication;

import org.anastasia.peopleinfoapplication.dao.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Test1 extends BaseIntegrationTest {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    void test() {
        String password = encoder.encode("password");
        System.out.println(password);
    }
}
