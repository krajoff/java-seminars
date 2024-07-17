package org.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.repository.UserRepository;
import org.example.models.User;
import org.example.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;
    private TokenService tokenService;


    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository, tokenService);
    }

    @Test
    public void testRegisterUser() throws SQLException {
        User user = new User("testuser", "testpassword", 0d);
        when(userRepository.saveUser(user)).thenReturn(user);

        User registeredUser = userService.registrateUser(user.getLogin(), user.getPassword());

        assertNotNull(registeredUser);
        assertEquals(user.getLogin(), registeredUser.getLogin());
        verify(userRepository, times(1)).saveUser(user);
    }

}

