package org.example.util;

import org.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CRUDUser {
    private static String INSERT_USER =
            "INSERT INTO users(login, password, balance) VALUES (?, ?, ?)";
    private static String SELECT_ALL_USER =
            "SELECT * FROM users";
    private static String UPDATE_BALANCE_BY_LOGIN =
            "UPDATE users SET balance = ? WHERE login = ?";
    private static String SELECT_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login = ?";
    private static String DELETE_USER_BY_LOGIN =
            "DELETE FROM users WHERE login = ?";

    public static List<User> findAllUser() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                double balance = rs.getDouble("balance");
                users.add(new User(id, login, password, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static User findUserByLogin(String login) {
        User user = null;
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String password = rs.getString("password");
            double balance = rs.getDouble("balance");
            user = new User(id, login, password, balance);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static void deleteUserByLogin(String login) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
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
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDouble(3, user.getBalance());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBalanceByLogin(String login, double balance) {
        try (Connection connection = DBUtils.getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(UPDATE_BALANCE_BY_LOGIN)) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setString(2, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
