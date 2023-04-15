package org.anastasia.peopleinfoapplication.service;

import org.anastasia.peopleinfoapplication.exception.BookNotFoundException;
import org.anastasia.peopleinfoapplication.model.Author;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.repository.AuthorRepository;
import org.anastasia.peopleinfoapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Book save(Book book) {
        Author author = book.getAuthor();
        authorRepository.findByFirstNameAndLastNameIgnoreCase(author.getFirstName(), author.getLastName())
                .ifPresentOrElse(book::setAuthor, () -> authorRepository.save(author));
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public List<Book> saveAll(List<Book> books) {
        Map<String, Author> uniqueConstraintAuthorMap = new HashMap<>();
        List<Author> authorsBatch = new LinkedList<>();
        books.stream()
                .map(Book::getAuthor)
                .forEach(author -> uniqueConstraintAuthorMap.put(author.getFirstNameAndLastName(), author));

        for (Author author : uniqueConstraintAuthorMap.values()) {
            authorRepository.findByFirstNameAndLastNameIgnoreCase(author.getFirstName(), author.getLastName())
                    .ifPresentOrElse(
                            existedAuthor -> uniqueConstraintAuthorMap.get(existedAuthor.getFirstNameAndLastName()).setId(existedAuthor.getId()),
                            () -> authorsBatch.add(author)
                    );
        }

        authorRepository.saveAll(authorsBatch);

        books.forEach(book -> book.setAuthor(uniqueConstraintAuthorMap.get(book.getAuthor().getFirstNameAndLastName())));

        return bookRepository.saveAll(books);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void untieBookFromPerson(@PathVariable("bookId") Long bookId) {
        Book foundBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        foundBook.setPerson(null);
    }
}