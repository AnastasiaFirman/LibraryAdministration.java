package org.anastasia.peopleinfoapplication.repository;

import org.anastasia.peopleinfoapplication.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    //void updatePersonId(Long personId, Long bookId);
}
