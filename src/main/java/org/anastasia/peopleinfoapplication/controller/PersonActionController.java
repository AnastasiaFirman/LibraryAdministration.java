package org.anastasia.peopleinfoapplication.controller;

import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.exception.BookNotFoundException;
import org.anastasia.peopleinfoapplication.mappers.PersonMapper;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.repository.BookRepository;
import org.anastasia.peopleinfoapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PersonActionController {
    private final PersonService personService;

    private final PersonMapper personMapper;

    @Autowired
    public PersonActionController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PutMapping("/api/v1/person/action/{personId}/book/{bookId}")
    public PersonDto setBookForPerson(@PathVariable("personId") Long personId,
                                      @PathVariable("bookId") Long bookId) {
        return personMapper.toPersonDto(personService.setBookForPerson(personId, bookId));
    }

    @PutMapping("/api/v1/book/action/{bookId}")
    public void untieBookFromPerson(@PathVariable("bookId") Long bookId) {
        personService.untieBookFromPerson(bookId);
    }
}
