package org.anastasia.libraryadministration.baseservice.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super("Книга не найдена");
    }
}
