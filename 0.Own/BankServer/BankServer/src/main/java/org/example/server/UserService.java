package org.example.server;

import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.util.JwtUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {
    private UserRepository userRepository;

    private JwtUtil jwtUtil;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        if (user != null && user.getPassword().equals(password))
            return JwtUtil.generateToken(login);
        return null;
    }

    public double getBalance(String login) throws SQLException {
        try (Connection conn = userRepository.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE login = ?");
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            return -1;
        }
    }

    public boolean transferMoney(String fromUser, String toUser, double amount) throws SQLException {
        try (Connection conn = userRepository.getConnection()) {
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement("SELECT balance FROM users WHERE login = ?");
            stmt.setString(1, fromUser);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");

                if (balance < amount) {
                    return false;
                }

                stmt = conn.prepareStatement("UPDATE users SET balance = balance - ? WHERE login = ?");
                stmt.setDouble(1, amount);
                stmt.setString(2, fromUser);
                stmt.executeUpdate();

                stmt = conn.prepareStatement("UPDATE users SET balance = balance + ? WHERE login = ?");
                stmt.setDouble(1, amount);
                stmt.setString(2, toUser);
                stmt.executeUpdate();

                conn.commit();
                return true;
            }
            return false;
        }
    }
}

