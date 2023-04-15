package org.anastasia.peopleinfoapplication.service;

import org.anastasia.peopleinfoapplication.model.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> saveAll(List<Book> books);
    void deleteById(Long id);
    void untieBookFromPerson(Long bookId);
}
