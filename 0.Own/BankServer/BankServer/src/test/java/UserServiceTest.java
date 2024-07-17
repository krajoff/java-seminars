import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.service.UserService;
import org.example.repository.UserRepository;
import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserServiceTest {
    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void testRegisterUser() {
        User user = new User("testuser", "testpassword");
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userService.registerUser(user.getLogin(), user.getPassword());

        assertNotNull(registeredUser);
        assertEquals(user.getLogin(), registeredUser.getLogin());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testAuthenticateUser() {
        User user = new User("testuser", "testpassword");
        when(userRepository.findByLogin(user.getLogin())).thenReturn(user);

        User authenticatedUser = userService.authenticateUser(user.getLogin(), user.getPassword());

        assertNotNull(authenticatedUser);
        assertEquals(user.getLogin(), authenticatedUser.getLogin());
        verify(userRepository, times(1)).findByLogin(user.getLogin());
    }
}

