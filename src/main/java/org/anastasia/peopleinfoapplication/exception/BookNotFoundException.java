package org.anastasia.peopleinfoapplication.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
        super("Книга не найдена");
    }
}
