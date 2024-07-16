package org.example.server;

import org.example.models.Transaction;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.util.JwtUtil;
import org.json.JSONObject;

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

    public User getUserById(long id) throws SQLException {
        return userRepository.findUserById(id);
    }

    public User registrateUser(String login, String password) throws SQLException {
        return (getUserByLogin(login) == null) ?
                userRepository.saveUser(new User(login, password, 0d)) : null;
    }

    public String authenticateUser(String login, String password) throws SQLException {
        User user = getUserByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            String token = tokenService.getValidTokenByUserId(user.getId());
            if (token == null) {
                token = JwtUtil.generateToken(login);
                tokenService.storeToken(user.getId(), token);
                return token;
            }
            return token;
        }
        return null;
    }

    public double getBalanceByLogin(String login) throws SQLException {
        User user = getUserByLogin(login);
        return (user != null) ? user.getBalance() : -1;
    }

    public double getBalanceById(long id) throws SQLException {
        User user = getUserById(id);
        return (user != null) ? user.getBalance() : -1;
    }

    public boolean transferMoney(Transaction transaction) throws SQLException {


        if (getUserByLogin() != null && userPayee != null) {
            userService.transferMoney()

            double newUserSenderBalance = userSender.getBalance()
                    - jsonRequest.getDouble("amount");
            if (newUserSenderBalance >= 0) {
                userSender.setBalance(newUserSenderBalance);
                userPayee.setBalance(userPayee.getBalance() +
                        jsonRequest.getDouble("amount"));

                sendResponse(out, 200, new
                        JSONObject().put("balance", userSender.getBalance()).toString());
                logger.info(String.format("Balance is %f", newUserSenderBalance));
            }


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

