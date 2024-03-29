import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static String INSERT_USER =
            "INSERT INTO users(Login, Email, PasswordHash) VALUES (?, ?, ?)";
    private static String SELECT_ALL_USER =
            "SELECT * FROM users";
    private static String UPDATE_PASSWORD =
            "UPDATE users SET PasswordHash = ? WHERE id = ?";
    private static String SELECT_USER_BY_ID =
            "SELECT * FROM users WHERE id = ?";
    private static String DELETE_USER_BY_ID =
            "DELETE FROM users WHERE id = ?";

    public static List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("Login");
                String email = rs.getString("Email");
                String passwordHash = rs.getString("PasswordHash");
                users.add(new User(id, login, email, passwordHash));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User findUserById(int id) {
        User user = null;
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            String login = rs.getString("Login");
            String email = rs.getString("Email");
            String passwordHash = rs.getString("PasswordHash");
            user = new User(id, login, email, passwordHash);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void deleteUserById(int id) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(User user) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPasswordHash());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePasswordById(int id, String passwordHash) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, passwordHash);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
