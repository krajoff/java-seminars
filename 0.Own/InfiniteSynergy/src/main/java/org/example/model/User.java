package org.example.model;

import lombok.Getter;
import lombok.Setter;
@Getter
public class User {
    private Long id;
    @Setter
    private String login;
    @Setter
    private String password;
    @Setter
    private double balance;
    public User(){};
    public User(long id, String login, String password, double balance) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
    }
}

