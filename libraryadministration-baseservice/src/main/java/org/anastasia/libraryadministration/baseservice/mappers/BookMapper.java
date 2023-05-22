package org.anastasia.libraryadministration.baseservice.mappers;

import org.anastasia.libraryadministration.baseservice.dto.BookDto;
import org.anastasia.libraryadministration.baseservice.model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toEntity(BookDto bookDto);
}
