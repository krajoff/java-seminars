package org.example.service;

import org.example.repository.TokenRepository;

import java.sql.SQLException;

public class TokenService {
    private final TokenRepository tokenRepository;

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void storeToken(long userId, String token) throws SQLException {
        tokenRepository.storeToken(userId, token);
    }

    public long validateToken(String token) throws SQLException {
        return tokenRepository.validateToken(token);
    }

    public String getValidTokenByUserId(long id) throws SQLException {
        return tokenRepository.getValidTokenByUserId(id);
    }
}
