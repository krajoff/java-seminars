package org.example.repository;

import java.sql.*;
import java.time.LocalDateTime;

public class TokenRepository {
    private final Connection connection;
    private UserRepository userRepository;

    public TokenRepository(Connection connection) {
        this.connection = connection;
    }

    private void storeToken(long userId, String token) throws SQLException {
        String sql = "INSERT INTO banking.tokens (user_id, token, expires_at) " +
                "VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setString(2, token);
            statement.setTimestamp(3,
                    Timestamp.valueOf(LocalDateTime.now().plusHours(1)));
            statement.executeUpdate();
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
