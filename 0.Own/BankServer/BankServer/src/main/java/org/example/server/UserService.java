package org.example.server;

import org.example.models.User;
import org.example.repository.TokenRepository;
import org.example.repository.UserRepository;
import org.example.util.JwtUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository,
                       TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User getUserByLogin(String login) throws SQLException {
        return userRepository.findUserByLogin(login);
    }

    public User registrateUser(String login, String password) throws SQLException {
        User user = new User(login, password, 0d);
        return userRepository.saveUser(user);
    }

    public String authenticateUser(String login, String password) throws SQLException {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            String token = JwtUtil.generateToken(login);
            tokenService.storeToken(user.getId(), token);
            return token;
        }
        return null;
    }

    public double getBalance(String login) throws SQLException {
        try (Connection connection = userRepository.getConnection()) {
            String query = "SELECT balance FROM users WHERE login = ?";
            PreparedStatement statement = connection
                    .prepareStatement(query);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            return -1;
        }
    }

    public boolean transferMoney(String fromUser, String toUser, double amount) throws SQLException {
        try (Connection connection = userRepository.getConnection()) {
            connection.setAutoCommit(false);
            String query = "SELECT balance FROM users WHERE login = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, fromUser);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < amount)
                    return false;

                statement = connection.prepareStatement("UPDATE users SET balance = balance - ? WHERE login = ?");
                statement.setDouble(1, amount);
                statement.setString(2, fromUser);
                statement.executeUpdate();

                statement = connection.prepareStatement("UPDATE users SET balance = balance + ? WHERE login = ?");
                statement.setDouble(1, amount);
                statement.setString(2, toUser);
                statement.executeUpdate();

                connection.commit();
                return true;
            }
            return false;
        }
    }
}

