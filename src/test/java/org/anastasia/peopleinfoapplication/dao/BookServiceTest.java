package org.anastasia.peopleinfoapplication.dao;

import org.anastasia.peopleinfoapplication.TestDataProvider;
import org.anastasia.peopleinfoapplication.model.Author;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.model.Person;
import org.anastasia.peopleinfoapplication.repository.AuthorRepository;
import org.anastasia.peopleinfoapplication.repository.BookRepository;
import org.anastasia.peopleinfoapplication.service.BookService;
import org.anastasia.peopleinfoapplication.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BookServiceTest extends BaseIntegrationTest {
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final TestDataProvider dataProvider;
    private final PersonService personService;

    @Autowired
    public BookServiceTest(BookService bookService, BookRepository bookRepository, AuthorRepository authorRepository, TestDataProvider dataProvider, PersonService personService) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.dataProvider = dataProvider;
        this.personService = personService;
    }

    @AfterEach
    void deleteAll() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
    }

    @Test
    @Transactional
    void saveBookWithNewAuthorTest() {
        Author author = dataProvider.buildAuthor(1);
        Book book = dataProvider.buildBook(1);
        book.setAuthor(author);
        Book savedBook = bookService.save(book);
        Book foundBook = bookRepository.findById(savedBook.getId()).get();
        Assertions.assertEquals(savedBook, foundBook);
    }

    @Test
    void saveBookWithExistingAuthor() {
        Author author1 = dataProvider.buildAuthor(1);
        Book book1 = dataProvider.buildBook(1);
        book1.setAuthor(author1);
        Book savedBook1 = bookService.save(book1);

        author1 = dataProvider.buildAuthor(1);
        Book book2 = dataProvider.buildBook(2);
        book2.setAuthor(author1);
        Book savedBook2 = bookService.save(book2);
        Assertions.assertEquals(2, bookRepository.findAll().size());
        Assertions.assertEquals(savedBook1.getAuthor().getId(), savedBook2.getAuthor().getId());
    }

    @Test
    void saveAllBooksWithNewAuthorTest() {
        Author author1 = dataProvider.buildAuthor(1);
        Author author2 = dataProvider.buildAuthor(2);
        Book book1 = dataProvider.buildBook(1);
        Book book2 = dataProvider.buildBook(2);
        book1.setAuthor(author1);
        book2.setAuthor(author2);
        bookService.saveAll(List.of(book1, book2));
        Assertions.assertEquals(2, bookRepository.findAll().size());
        Assertions.assertEquals(2, authorRepository.findAll().size());
    }

    @Test
    void saveAllBooksWithSameAuthorTest() {
        Author author1 = dataProvider.buildAuthor(1);
        Book book1 = dataProvider.buildBook(1);
        book1.setAuthor(author1);
        Author author11 = dataProvider.buildAuthor(1);
        Book book2 = dataProvider.buildBook(2);
        book2.setAuthor(author11);
        bookService.saveAll(List.of(book1, book2));
        Assertions.assertEquals(2, bookRepository.findAll().size());
        Assertions.assertEquals(1, authorRepository.findAll().size());
        Assertions.assertEquals(book1.getAuthor(), book2.getAuthor());

    }

    @Test
    void deleteBookByIdTest() {
        Author author = dataProvider.buildAuthor(1);
        Book book = dataProvider.buildBook(1);
        book.setAuthor(author);
        Book savedBook = bookService.save(book);
        bookService.deleteById(savedBook.getId());
        Assertions.assertEquals(0, bookRepository.findAll().size());
    }
    @Test
    void untieBookFromPersonTest() {
        Book book = dataProvider.buildBook(1);
        book.setAuthor(dataProvider.buildAuthor(1));
        Long bookId = bookService.save(book).getId();
        Long personId = personService.save(dataProvider.buildPerson(1)).getId();

        personService.setBookForPerson(personId, bookId);
        bookService.untieBookFromPerson(bookId);

        Person foundPerson = personService.findById(personId);
        Assertions.assertEquals(0, foundPerson.getBooks().size());
    }
}
