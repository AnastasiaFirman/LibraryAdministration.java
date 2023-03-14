package org.anastasia.peopleinfoapplication.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PersonDto {
    private Long id;

    private String firstName;

    private String lastName;

    private int age;
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "dd.MM.yyyy")
    private String dateOfBirth;

    private List<BookDto> books = new ArrayList<>();
}
