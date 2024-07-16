package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String login;
    private String password;
    private Double balance;
    private String token;

    public User(String login, String password, Double balance) {
        this.login = login;
        this.password = password;
        this.balance = balance;
    }
}
