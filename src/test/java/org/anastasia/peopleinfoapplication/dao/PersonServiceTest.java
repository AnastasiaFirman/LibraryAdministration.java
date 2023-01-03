package org.anastasia.peopleinfoapplication.dao;

import org.anastasia.peopleinfoapplication.model.Person;
import org.anastasia.peopleinfoapplication.service.PersonService;
import org.anastasia.peopleinfoapplication.service.PersonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

public class PersonServiceTest {
    private final PersonService personService = new ClassPathXmlApplicationContext("file:src/main/resources/applicationContext.xml")
            .getBean("personServiceImpl", PersonServiceImpl.class);

    @AfterEach
    void deleteAll() {
        personService.deleteAll();
    }

    @Test
    void saveAndFindTest() {
        int age = 10;
        LocalDate testDateOfBirth = LocalDate.now().minusYears(age);

        Person personForSave = new Person("Katya", "Ivanova", testDateOfBirth);
        personService.save(personForSave);

        Person foundPerson = personService.findById(personForSave.getId());
        Assertions.assertEquals(age, foundPerson.getAge());
        Assertions.assertEquals(personForSave, foundPerson);
    }

    @Test
    void findAllTest() {
        personService.save(new Person("Nata", "Ivanova", 24, LocalDate.of(1998, 3, 21)));
        personService.save(new Person("Natalia", "Popova", 26, LocalDate.of(1996, 4, 11)));
        Assertions.assertEquals(2, personService.findAll().size());
    }

    @Test
    void deleteByIdTest() {
        Person person = new Person(1L, "Natalia", "Popova", 26, LocalDate.of(1996, 4, 11));
        personService.deleteById(person.getId());
        Assertions.assertEquals(0, personService.findAll().size());
    }
}
