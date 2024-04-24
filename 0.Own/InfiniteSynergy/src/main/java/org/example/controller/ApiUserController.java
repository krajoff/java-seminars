package org.example.controller;

import org.example.service.UserService;
import org.example.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        if (userService.getByUsername(username) != null) {
            User user = userService.getByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{username}")
    public User updateUser(@PathVariable String username,
                           @RequestBody User user) {
        return userService.updateByUsername(username, user);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        if (userService.getByUsername(username) != null) {
            userService.deleteByUsername(username);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



