package org.example.service;

import org.example.model.Role;
import org.example.model.User;
import org.example.exception.UserNotFoundException;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User getByUsername(String username) {
        if (userRepository.findByUsername(username) != null)
            return userRepository.findByUsername(username);
        else
            throw new UserNotFoundException("User not found");
    }

    public User updateById(Long id, User user) {
        User existingUser = getById(id);
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    public User updateByUsername(String username, User user) {
        User existingUser = getByUsername(username);
        existingUser.setPassword(user.getPassword());
        return existingUser;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public boolean saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            System.out.println("User already existed");
            return false;
        } else {
            user.setRole(Role.USER);
            user.setPassword(user.getPassword());
            userRepository.save(user);
            return true;
        }
    }
}
