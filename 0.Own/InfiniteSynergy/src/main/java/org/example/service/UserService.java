package org.example.service;

import org.example.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getById(Long id);

    User getByUsername(String username);

    User create(User user);

    User updateById(Long id, User user);

    User updateByUsername(String username, User user);

    void deleteById(Long id);

    void deleteByUsername(String username);

    boolean save(User user);

}
