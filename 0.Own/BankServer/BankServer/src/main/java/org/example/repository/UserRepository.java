package org.example.repository;

import org.example.models.Transaction;
import org.example.models.User;
import org.example.handler.ClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class UserRepository {
    private final Connection connection;

    private static final Logger logger = LoggerFactory
            .getLogger(ClientHandler.class);

    public UserRepository() throws SQLException {
        Properties props = new Properties();
        try (InputStream input = UserRepository.class
                .getClassLoader()
                .getResourceAsStream(("application.properties"))) {
            props.load(input);
        } catch (IOException e) {
            logger.error("No connection", e);
        }
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");
        connection = DriverManager.getConnection(url, user, password);
        logger.info("Connection to database is successful...");
        initDatabase();
    }

    private void initDatabase() {
        try (Statement statement = connection.createStatement()) {
            String initScript = "CREATE SCHEMA IF NOT EXISTS banking; " +
                    "CREATE TABLE IF NOT EXISTS banking.users (" +
                    "id BIGSERIAL PRIMARY KEY, " +
                    "login VARCHAR(80) NOT NULL UNIQUE, " +
                    "password VARCHAR(256) NOT NULL, " +
                    "balance DOUBLE PRECISION DEFAULT 0);";
            statement.execute(initScript);
            logger.info("Database initialized successfully.");
        } catch (SQLException e) {
            logger.error("Error to create table 'users'", e);
        }
    }

    private void modifyDatabase() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("ALTER TABLE banking.users " +
                    "ALTER COLUMN password TYPE VARCHAR(256)");
            logger.info("Database modify successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findUserByLogin(String login) throws SQLException {
        String query = "SELECT * FROM banking.users WHERE login = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setBalance(rs.getDouble("balance"));
                return user;
            } else {
                return null;
            }
        }
    }

    public User saveUser(User user) throws SQLException {
        User existingUser = findUserByLogin(user.getLogin());
        if (existingUser == null) {
            String query = "INSERT INTO banking.users " +
                    "(login, password, balance) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getLogin());
                statement.setString(2, user.getPassword());
                statement.setDouble(3, user.getBalance());
                statement.executeUpdate();
                logger.info(String.format("New user %s is created.",
                        user.getLogin()));
            }
            return user;
        } else {
            logger.warn("User has already created.");
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        User existingUser = findUserByLogin(user.getLogin());
        if (existingUser != null) {
            String query = "UPDATE banking.users SET balance = ? WHERE login = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setDouble(1, user.getBalance());
                stmt.setString(2, user.getLogin());
                stmt.executeUpdate();
                logger.info(String.format("User %s is updated.",
                        user.getLogin()));
            }
        } else {
            logger.info("User is not exist");
        }
    }

    public void saveTransaction(Transaction transaction) throws SQLException {
        String query = "INSERT INTO transactions " +
                "(from_user, to_user, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, transaction.getFromUser());
            stmt.setString(2, transaction.getToUser());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setTimestamp(4,
                    Timestamp.valueOf(transaction.getTimestamp()));
            stmt.executeUpdate();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
