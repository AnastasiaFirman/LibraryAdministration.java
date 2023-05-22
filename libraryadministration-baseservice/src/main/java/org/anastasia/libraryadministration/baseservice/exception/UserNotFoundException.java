package org.anastasia.libraryadministration.baseservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("Пользователь с таким id не найден");

    }
}
