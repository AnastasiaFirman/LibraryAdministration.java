package org.anastasia.peopleinfoapplication.mappers;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public PersonMapper personMapper() {
        return Mappers.getMapper(PersonMapper.class);
    }
    @Bean
    public BookMapper bookMapper() {
        return Mappers.getMapper(BookMapper.class);
    }
}
