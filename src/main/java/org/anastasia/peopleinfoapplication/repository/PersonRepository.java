package org.anastasia.peopleinfoapplication.repository;

import org.anastasia.peopleinfoapplication.model.Person;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    //@Query(value = "select person from Person person join fetch person.books where person.id = :id")
    @EntityGraph(attributePaths = "books")
    Optional<Person> findById(Long id);
}
