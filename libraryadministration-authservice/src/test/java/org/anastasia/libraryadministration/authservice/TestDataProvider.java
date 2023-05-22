package org.anastasia.libraryadministration.authservice;

import org.anastasia.libraryadministration.authservice.model.User;

public class TestDataProvider {
    public static User buildUser(int index) {
        return User.builder()
                .username("name" + index)
                .password("password" + index)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();
    }
}
