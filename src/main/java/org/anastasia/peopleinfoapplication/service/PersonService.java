package org.anastasia.peopleinfoapplication.service;

import org.anastasia.peopleinfoapplication.model.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    Person findById(Long id);

    List<Person> findAll();
    List<Person> findAll(String lastName);

    void deleteById(Long id);

    Person update(Long id, Person person);

    Person setBookForPerson(Long personId, Long bookId);

}
