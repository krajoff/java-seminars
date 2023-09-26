package seminars.third.tdd;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // Тут можно хранить аутентифицированных пользователей
    List<User> data = new ArrayList<>();

    public void addUser(User user) {
        data.add(user);
    }

    public User findByName(String username) {
        for (User user : data) {
            if (user.name.equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void logOutAllExceptAdmin() {
        for (User user : data) {
            if (!user.isAdmin) {
                user.setAuthenticate(false);
            }
        }
    }

}