package org.example.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Token {
    private String token;
    private Timestamp expiresAt;
}
