package org.anastasia.peopleinfoapplication.service;

import org.anastasia.peopleinfoapplication.exception.BookNotFoundException;
import org.anastasia.peopleinfoapplication.exception.UserNotFoundException;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.model.Person;
import org.anastasia.peopleinfoapplication.repository.BookRepository;
import org.anastasia.peopleinfoapplication.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final BookRepository bookRepository;


    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Person save(Person person) {
        int age = calcAge(person);
        person.setAge(age);
        return personRepository.save(person);
    }


    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<Person> findAll(String lastName) {
        return personRepository.findAllByLastNameIgnoreCase(lastName);
    }

    @Override
    public void deleteById(Long id) {
        try {
            personRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public Person update(Long id, Person person) {
        int age = calcAge(person);
        person.setAge(age);
        person.setId(id);
        return personRepository.save(person);
    }

    @Transactional
    @Override
    public Person setBookForPerson(Long personId, Long bookId) {
        Person foundPerson = personRepository.findById(personId).orElseThrow(UserNotFoundException::new);
        Book foundBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        foundBook.setPerson(foundPerson);
        foundPerson.getBooks().add(foundBook);
        return foundPerson;
    }

    @Transactional
    @Override
    public void untieBookFromPerson(@PathVariable("bookId") Long bookId) {
        Book foundBook = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        foundBook.setPerson(null);
    }

    private int calcAge(Person person) {
        Period difference = Period.between(person.getDateOfBirth(), LocalDate.now());
        int calculatedAge = difference.getYears();
        return calculatedAge;
    }
}
