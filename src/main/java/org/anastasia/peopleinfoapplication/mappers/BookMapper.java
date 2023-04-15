package org.anastasia.peopleinfoapplication.mappers;

import org.anastasia.peopleinfoapplication.dto.BookDto;
import org.anastasia.peopleinfoapplication.model.Book;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BookMapper {
    BookDto toBookDto(Book book);
    Book toEntity(BookDto bookDto);
}
