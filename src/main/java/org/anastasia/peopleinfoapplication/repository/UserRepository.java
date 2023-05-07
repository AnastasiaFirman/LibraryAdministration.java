package org.anastasia.peopleinfoapplication.repository;

import org.anastasia.peopleinfoapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
