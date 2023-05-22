package org.anastasia.libraryadministration.baseservice.mappers;

import org.anastasia.libraryadministration.baseservice.TestDataProvider;
import org.anastasia.libraryadministration.baseservice.dto.BookDto;
import org.anastasia.libraryadministration.baseservice.dto.PersonDto;
import org.anastasia.libraryadministration.baseservice.dto.ShortPersonDto;
import org.anastasia.libraryadministration.baseservice.model.Book;
import org.anastasia.libraryadministration.baseservice.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PersonMapperTest {
    private final TestDataProvider dataProvider = new TestDataProvider();
    private final PersonMapper personMapper = new PersonMapperImpl();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Test
    void toShortDtoTest() {
        Person person = dataProvider.buildPerson(1);
        List<Book> books = new ArrayList<>();
        books.add(dataProvider.buildBook(1));
        person.setBooks(books);

        ShortPersonDto shortPersonDto = personMapper.toShortDto(person);

        Assertions.assertEquals(person.getFirstName(), shortPersonDto.getFirstName());
        Assertions.assertEquals(person.getLastName(), shortPersonDto.getLastName());
        Assertions.assertEquals(person.getAge(), shortPersonDto.getAge());
        Assertions.assertEquals(person.getDateOfBirth().format(formatter), shortPersonDto.getDateOfBirth());
    }
    @Test
    void toPersonDtoTest() {
        Person person = dataProvider.buildPerson(1);
        List<Book> books = new ArrayList<>();
        books.add(dataProvider.buildBook(1));
        person.setBooks(books);

        PersonDto personDto = personMapper.toPersonDto(person);

        Assertions.assertEquals(person.getFirstName(), personDto.getFirstName());
        Assertions.assertEquals(person.getLastName(), personDto.getLastName());
        Assertions.assertEquals(person.getAge(), personDto.getAge());
        Assertions.assertEquals(person.getDateOfBirth().format(formatter), personDto.getDateOfBirth());
        Assertions.assertEquals(person.getBooks().get(0).getTitle(), personDto.getBooks().get(0).getTitle() );
    }
    @Test
    void fromPersonDtoToEntityTest() {
        PersonDto personDto = dataProvider.buildPersonDto(1);
        List<BookDto> bookDtos = new ArrayList<>();
        bookDtos.add(dataProvider.buildBookDto(1));
        personDto.setBooks(bookDtos);

        Person person = personMapper.toEntity(personDto);

        Assertions.assertEquals(person.getFirstName(), personDto.getFirstName());
        Assertions.assertEquals(person.getLastName(), personDto.getLastName());
        Assertions.assertEquals(person.getAge(), personDto.getAge());
        Assertions.assertEquals(person.getDateOfBirth().format(formatter), personDto.getDateOfBirth());
        Assertions.assertEquals(person.getBooks().get(0).getTitle(), personDto.getBooks().get(0).getTitle() );
    }

    @Test
    void fromShortPersonDtoToEntityTest() {
        ShortPersonDto shortPersonDto = dataProvider.buildShortPersonDto(1);

        Person person = personMapper.toEntity(shortPersonDto);

        Assertions.assertEquals(person.getFirstName(), shortPersonDto.getFirstName());
        Assertions.assertEquals(person.getLastName(), shortPersonDto.getLastName());
        Assertions.assertEquals(person.getAge(), shortPersonDto.getAge());
        Assertions.assertEquals(person.getDateOfBirth().format(formatter), shortPersonDto.getDateOfBirth());
    }

}
