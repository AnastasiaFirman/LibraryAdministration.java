package org.anastasia.peopleinfoapplication.dto;

import lombok.*;
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String firstName;
    private String lastName;
}
