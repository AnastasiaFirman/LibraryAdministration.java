package org.anastasia.libraryadministration.baseservice.service;

import org.anastasia.libraryadministration.baseservice.BaseIntegrationTest;
import org.anastasia.libraryadministration.baseservice.TestDataProvider;
import org.anastasia.libraryadministration.baseservice.model.Author;
import org.anastasia.libraryadministration.baseservice.model.Book;
import org.anastasia.libraryadministration.baseservice.model.Person;
import org.anastasia.libraryadministration.baseservice.repository.AuthorRepository;
import org.anastasia.libraryadministration.baseservice.repository.BookRepository;
import org.anastasia.libraryadministration.baseservice.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class PersonServiceTest extends BaseIntegrationTest {
    private final PersonService personService;
    private final PersonRepository personRepository;
    private final TestDataProvider dataProvider;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;



    @Autowired
    public PersonServiceTest(PersonService personService, PersonRepository personRepository, TestDataProvider dataProvider, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
        this.dataProvider = dataProvider;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @AfterEach
    void deleteAll() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();
        personRepository.deleteAll();
    }
    @Test
    void saveAndFindTest() {
        Person personForSave = dataProvider.buildPerson(1);
        personService.save(personForSave);
        Person foundPerson = personService.findById(personForSave.getId());
        Assertions.assertEquals(personForSave, foundPerson);
    }

    @Test
    void findAllTest() {
        personService.save(dataProvider.buildPerson(1));
        personService.save(dataProvider.buildPerson(2));
        Assertions.assertEquals(2, personService.findAll().size());
    }

    @Test
    void deleteByIdTest() {
        Person person = dataProvider.buildPerson(1);
        Person savedPerson = personService.save(person);
        personService.deleteById(savedPerson.getId());
        Assertions.assertEquals(0, personService.findAll().size());
    }
    @Test
    void setBookForPerson() {
        Person person = dataProvider.buildPerson(1);
        Book book = dataProvider.buildBook(1);
        Author author = dataProvider.buildAuthor(1);
        book.setAuthor(author);

        personRepository.save(person);
        authorRepository.save(author);
        bookRepository.save(book);

        personService.setBookForPerson(person.getId(), book.getId());
        Person foundPerson = personRepository.findById(person.getId()).get();
        Book personBook = foundPerson.getBooks().get(0);

        Assertions.assertEquals(personBook.getId(), book.getId());
    }
}
