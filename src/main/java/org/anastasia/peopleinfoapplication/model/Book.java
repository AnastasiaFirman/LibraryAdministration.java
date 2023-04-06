package org.anastasia.peopleinfoapplication.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
