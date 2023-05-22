package org.anastasia.libraryadministration.baseservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class PersonDto {
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String dateOfBirth;

    private List<BookDto> books = new ArrayList<>();
}
