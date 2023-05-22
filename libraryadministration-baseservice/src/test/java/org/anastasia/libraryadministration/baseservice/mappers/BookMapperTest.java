package org.anastasia.libraryadministration.baseservice.mappers;

import org.anastasia.libraryadministration.baseservice.TestDataProvider;
import org.anastasia.libraryadministration.baseservice.dto.AuthorDto;
import org.anastasia.libraryadministration.baseservice.dto.BookDto;
import org.anastasia.libraryadministration.baseservice.model.Author;
import org.anastasia.libraryadministration.baseservice.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BookMapperTest {
    private final TestDataProvider dataProvider = new TestDataProvider();
    private final BookMapper bookMapper = new BookMapperImpl();
    @Test
    void toEntityTest() {
        BookDto bookDto = dataProvider.buildBookDto(1);
        AuthorDto authorDto = dataProvider.buildAuthorDto(1);
        bookDto.setAuthor(authorDto);

        Book bookEntity = bookMapper.toEntity(bookDto);
        Assertions.assertEquals(bookDto.getTitle(), bookEntity.getTitle());
        Assertions.assertEquals(bookDto.getAuthor().getFirstName(), bookEntity.getAuthor().getFirstName());
        Assertions.assertEquals(bookDto.getAuthor().getLastName(), bookEntity.getAuthor().getLastName());
    }
    @Test
    void toBookDtoTest() {
        Book bookEntity = dataProvider.buildBook(1);
        Author author = dataProvider.buildAuthor(1);
        bookEntity.setAuthor(author);

        BookDto bookDto = bookMapper.toBookDto(bookEntity);
        Assertions.assertEquals(bookEntity.getTitle(), bookDto.getTitle());
        Assertions.assertEquals(bookEntity.getAuthor().getFirstName(), bookDto.getAuthor().getFirstName());
        Assertions.assertEquals(bookEntity.getAuthor().getLastName(), bookDto.getAuthor().getLastName());
    }

}