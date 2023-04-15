package org.anastasia.peopleinfoapplication.controller;

import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.dto.ShortPersonDto;
import org.anastasia.peopleinfoapplication.mappers.PersonMapper;
import org.anastasia.peopleinfoapplication.model.Person;
import org.anastasia.peopleinfoapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {
    private final PersonService personService;

    private final PersonMapper personMapper;

    @Autowired
    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping("/api/v1/person")
    public List<ShortPersonDto> findAll(@RequestParam(name = "lastName", required = false) String lastName) {
        List<Person> list = null;
        if (lastName == null || lastName.isEmpty()) {
            list = personService.findAll();
        } else {
            list = personService.findAll(lastName);
        }
        return list.stream().map(personMapper::toShortDto).collect(Collectors.toList());
    }

    @GetMapping("/api/v1/person/{id}")
    public PersonDto findById(@PathVariable("id") Long id) {
        return personMapper.toPersonDto(personService.findById(id));
    }

    @PostMapping("/api/v1/person")
    public ShortPersonDto save(@RequestBody ShortPersonDto shortPersonDto) {
        return personMapper.toShortDto(personService.save(personMapper.toEntity(shortPersonDto)));
    }

    @PutMapping("/api/v1/person/{id}")
    public ShortPersonDto update(@RequestBody ShortPersonDto shortPersonDto, @PathVariable("id") Long id) {
        return personMapper.toShortDto(personService.update(id, personMapper.toEntity(shortPersonDto)));
    }

    @DeleteMapping("/api/v1/person/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        personService.deleteById(id);
    }
    @PutMapping("/api/v1/person/{personId}/book/{bookId}")
    public PersonDto setBookForPerson(@PathVariable("personId") Long personId,
                                      @PathVariable("bookId") Long bookId) {
        return personMapper.toPersonDto(personService.setBookForPerson(personId, bookId));
    }
}