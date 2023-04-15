package org.anastasia.peopleinfoapplication.controller;

import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.mappers.PersonMapper;
import org.anastasia.peopleinfoapplication.service.BookService;
import org.anastasia.peopleinfoapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonActionController {
    private final PersonService personService;
    private final PersonMapper personMapper;

    @Autowired
    public PersonActionController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

}
