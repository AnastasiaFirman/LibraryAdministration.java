package org.anastasia.libraryadministration.baseservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private AuthorDto author;
}
