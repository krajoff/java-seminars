package org.example.server;

import org.example.repository.TokenRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void storeToken(long userId, String token) throws SQLException {
        tokenRepository.storeToken(userId, token);
    }

    public boolean validateToken(String token) throws SQLException {
        return tokenRepository.validateToken(token);
    }
}
