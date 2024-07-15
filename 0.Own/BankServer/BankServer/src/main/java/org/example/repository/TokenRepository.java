package org.example.repository;

import lombok.Getter;
import org.example.handler.ClientHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;

public class TokenRepository {
    private final Connection connection;

    private static final Logger logger = LoggerFactory
            .getLogger(ClientHandler.class);

    public TokenRepository(Connection connection) {
        this.connection = connection;
    }

    public void storeToken(long userId, String token) throws SQLException {
        String sql = "INSERT INTO banking.tokens (user_id, token, expires_at) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setString(2, token);
            statement.setTimestamp(3,
                    Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
            statement.executeUpdate();
            logger.info(String.format("New token for userId = %d is created.", userId));
        }
    }

    public boolean validateToken(String token) throws SQLException {
        String sql = "SELECT * FROM banking.tokens WHERE token = ? " +
                "AND expires_at > ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, token);
            statement.setTimestamp(2,
                    Timestamp.valueOf(LocalDateTime.now()));
            ResultSet rs = statement.executeQuery();
            return rs.next();
        }
    }

}
