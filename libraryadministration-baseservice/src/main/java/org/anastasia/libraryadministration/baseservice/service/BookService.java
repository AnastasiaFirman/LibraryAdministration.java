package org.anastasia.libraryadministration.baseservice.service;

import org.anastasia.libraryadministration.baseservice.model.Book;

import java.util.List;

public interface BookService {
    Book save(Book book);
    List<Book> saveAll(List<Book> books);
    void deleteById(Long id);
    void untieBookFromPerson(Long bookId);
}
