package org.anastasia.libraryadministration.baseservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Author {
    @Id
    @GeneratedValue(generator = "author_id_seq")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    private List<Book> books;

    public String getFirstNameAndLastName() {
        return String.format("%s%s", firstName, lastName);
    }
}
