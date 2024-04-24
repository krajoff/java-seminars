package org.example.repository;

import org.example.model.User;
import org.example.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username) throws UserNotFoundException;
    void deleteByUsername(String username) throws UserNotFoundException;
}
