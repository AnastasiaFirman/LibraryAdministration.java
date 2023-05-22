package org.anastasia.libraryadministration.baseservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ShortPersonDto {
    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String dateOfBirth;

}
