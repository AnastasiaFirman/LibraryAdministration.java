package org.anastasia.peopleinfoapplication.converter;

import org.anastasia.peopleinfoapplication.dto.BookDto;
import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.dto.ShortPersonDto;
import org.anastasia.peopleinfoapplication.mappers.PersonMapper;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class PersonEntityDtoConverterTest {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    //private final PersonEntityDtoConverter personEntityDtoConverter = new PersonEntityDtoConverter();
    private PersonMapper personMapper;
    @Test
    void toShortDtoTest() {
        Person person = new Person(1L,"Katy", "Ivanova", 22,
                LocalDate.of(2000, 12, 12));
        ShortPersonDto shortPersonDto = personMapper.toShortDto(person);
        Assertions.assertEquals(person.getAge(), shortPersonDto.getAge());
        Assertions.assertEquals(person.getId(), shortPersonDto.getId());
        Assertions.assertEquals(person.getDateOfBirth().format(formatter), shortPersonDto.getDateOfBirth());
        Assertions.assertEquals(person.getFirstName(), shortPersonDto.getFirstName());
        Assertions.assertEquals(person.getLastName(), shortPersonDto.getLastName());
    }
    @Test
    void toPersonDtoTest() {
        List<Book> books = new ArrayList<>();
        Person person = new Person(10L,"Katy", "Ivanova", 22,
                LocalDate.of(2000, 12, 12), books);
        books.add(0, new Book(1L, "Шерлок Холмс", "Артур Конан Дойл", person));

        PersonDto personDto = personMapper.toPersonDto(person);

        Assertions.assertEquals(person.getAge(), personDto.getAge());
        Assertions.assertEquals(person.getId(), personDto.getId());
        Assertions.assertEquals(person.getDateOfBirth().format(formatter), personDto.getDateOfBirth());
        Assertions.assertEquals(person.getFirstName(), personDto.getFirstName());
        Assertions.assertEquals(person.getLastName(), personDto.getLastName());
        Assertions.assertEquals(person.getBooks().get(0).getAuthor(), personDto.getBooks().get(0).getAuthor());
        Assertions.assertEquals(person.getBooks().get(0).getTitle(), personDto.getBooks().get(0).getTitle());
    }
    @Test
    void toEntityTest() {
        List<BookDto> books = new ArrayList<>();
        books.add(new BookDto(1L, "Шерлок Холмс", "Артур Конан Дойл"));
        PersonDto personDto = new PersonDto(1L, "Katy", "Ivanova", 22,
                "12.12.2000", books);
        Person person = personMapper.toEntity(personDto);

        Assertions.assertEquals(personDto.getAge(), person.getAge());
        Assertions.assertEquals(personDto.getId(), person.getId());
        Assertions.assertEquals(personDto.getDateOfBirth(), person.getDateOfBirth().format(formatter));
        Assertions.assertEquals(personDto.getFirstName(), person.getFirstName());
        Assertions.assertEquals(personDto.getLastName(), person.getLastName());
        Assertions.assertEquals(personDto.getBooks().get(0).getAuthor(), person.getBooks().get(0).getAuthor());
        Assertions.assertEquals(personDto.getBooks().get(0).getTitle(), person.getBooks().get(0).getTitle());
    }
}