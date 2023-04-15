package org.anastasia.peopleinfoapplication;

import org.anastasia.peopleinfoapplication.dto.AuthorDto;
import org.anastasia.peopleinfoapplication.dto.BookDto;
import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.dto.ShortPersonDto;
import org.anastasia.peopleinfoapplication.model.Author;
import org.anastasia.peopleinfoapplication.model.Book;
import org.anastasia.peopleinfoapplication.model.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TestDataProvider {
    public BookDto buildBookDto(int index) {
        return BookDto.builder()
                .title("title" + index)
                .build();
    }

    public AuthorDto buildAuthorDto(int index) {
        return AuthorDto.builder()
                .firstName("firstName" + index)
                .lastName("lastName" + index)
                .build();
    }

    public Book buildBook(int index) {
        return Book.builder()
                .title("title" + index)
                .build();
    }

    public Author buildAuthor(int index) {
        return Author.builder()
                .firstName("firstName" + index)
                .lastName("lastName" + index)
                .build();
    }

    public Person buildPerson(int index) {
        return Person.builder()
                .firstName("firstName" + index)
                .lastName("lastName" + index)
                .age(index)
                .dateOfBirth(LocalDate.of(2000, 12, 21))
                .build();
    }

    public ShortPersonDto buildShortPersonDto(int index) {
        return ShortPersonDto.builder()
                .firstName("firstName" + index)
                .lastName("lastName" + index)
                .age(1 + index)
                .dateOfBirth("27.07.1986")
                .build();
    }

    public PersonDto buildPersonDto(int index) {
        return PersonDto.builder()
                .firstName("firstName" + index)
                .lastName("lastName" + index)
                .age(1 + index)
                .dateOfBirth("27.07.1986")
                .build();
    }
}
