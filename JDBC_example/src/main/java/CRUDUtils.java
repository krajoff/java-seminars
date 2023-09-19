import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {

    private static String INSERT_USER =
            "INSERT INTO users(Login, Email, PasswordHash) VALUES (?, ?, ?)";
    private static String SELECT_ALL_USER =
            "SELECT * FROM users";

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

    public static List<User> addUser(User user) {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_USER)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPasswordHash());
            preparedStatement.executeUpdate();

            PreparedStatement allStatement =
                    connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = allStatement.executeQuery();

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

}
