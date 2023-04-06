package org.anastasia.peopleinfoapplication.mappers;

import org.anastasia.peopleinfoapplication.dto.AuthorDto;
import org.anastasia.peopleinfoapplication.dto.PersonDto;
import org.anastasia.peopleinfoapplication.dto.ShortPersonDto;
import org.anastasia.peopleinfoapplication.model.Author;
import org.anastasia.peopleinfoapplication.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PersonMapper {
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    ShortPersonDto toShortDto(Person person);
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    PersonDto toPersonDto(Person person);
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    Person toEntity(PersonDto personDto);
}
