import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUDUtils {
    public static List<User> findAllUser(String query) {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
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
