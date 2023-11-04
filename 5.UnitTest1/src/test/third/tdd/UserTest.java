package third.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seminars.third.tdd.User;
import seminars.third.tdd.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    UserRepository userRepository;

    @BeforeEach
    void testInit() {
        userRepository = new UserRepository();
        User user1 = new User("Mike", "hash1", false);
        User user2 = new User("Nike", "hash2", false);
        User user3 = new User("Tom", "hash3", true);
        user1.authenticate("Mike","hash1");
        user2.authenticate("Nike","hash2");
        user3.authenticate("Tom","hash3");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);
    }

    @Test
    void testLogOutAllExceptAdmin(){
        userRepository.logOutAllExceptAdmin();
        assertTrue(userRepository.findByName("Tom").getIsAdmin());
        assertFalse(userRepository.findByName("Mike").getIsAdmin());
    }

}