package org.example.server;

import org.example.models.Transaction;
import org.example.models.User;
import org.example.repository.UserRepository;
import org.example.util.JwtUtil;
import org.example.util.LoggerUtil;

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

    public void transferMoney(Transaction transaction) throws SQLException {
        User userSender = userRepository.findUserByLogin(transaction.getFromUser());
        User userPayee = userRepository.findUserByLogin(transaction.getToUser());
        if (userSender != null && userPayee != null)
            if (userSender.getBalance() - transaction.getAmount() >= 0)
                userRepository.transaction(userSender, userPayee, transaction.getAmount());
            else
                LoggerUtil.logInfo("Not enough money");
    }

}

