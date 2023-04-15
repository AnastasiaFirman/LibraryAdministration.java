package org.anastasia.peopleinfoapplication.repository;

import org.anastasia.peopleinfoapplication.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

}
