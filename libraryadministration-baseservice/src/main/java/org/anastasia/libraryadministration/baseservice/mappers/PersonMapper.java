package org.anastasia.libraryadministration.baseservice.mappers;

import org.anastasia.libraryadministration.baseservice.dto.PersonDto;
import org.anastasia.libraryadministration.baseservice.dto.ShortPersonDto;
import org.anastasia.libraryadministration.baseservice.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper {
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    ShortPersonDto toShortDto(Person person);
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    ShortPersonDto toShortDto(ShortPersonDto shortPersonDto);
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    PersonDto toPersonDto(Person person);
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    Person toEntity(PersonDto personDto);
    @Mapping(target = "dateOfBirth", dateFormat = "dd.MM.yyyy")
    Person toEntity(ShortPersonDto shortPersonDto);
}
