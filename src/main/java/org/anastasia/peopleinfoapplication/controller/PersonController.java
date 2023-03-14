package org.anastasia.peopleinfoapplication.controller;

import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.dto.ShortPersonDto;
import org.anastasia.peopleinfoapplication.mappers.PersonMapper;
import org.anastasia.peopleinfoapplication.model.Person;
import org.anastasia.peopleinfoapplication.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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
    public List<ShortPersonDto> findAll() {
        return personService.findAll().stream()
                .map(personMapper::toShortDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/api/v1/person/{id}")
    public PersonDto findById(@PathVariable("id") Long id) {
        return personMapper.toPersonDto(personService.findById(id));
    }

    @PostMapping("/api/v1/person")
    public PersonDto save(@RequestBody PersonDto personDto) {
        Person person = personMapper.toEntity(personDto);
        Person savedPerson = personService.save(person);
        return personMapper.toPersonDto(savedPerson);
    }

    @PutMapping("/api/v1/person/{id}")
    public PersonDto update(@RequestBody PersonDto personDto, @PathVariable("id") Long id) {
        Person person = personMapper.toEntity(personDto);
        Person updatedPerson = personService.update(id, person);
        return personMapper.toPersonDto(updatedPerson);
    }

    @DeleteMapping("/api/v1/person/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        personService.deleteById(id);
    }
}
