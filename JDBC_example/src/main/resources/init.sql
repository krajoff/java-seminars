CREATE SCHEMA IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS users;
CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT,
    Login VARCHAR(80),
    Email VARCHAR(80),
    PasswordHash VARCHAR(50));

INSERT INTO users(Login, Email, PasswordHash) VALUES ("Me", "me@me.ru", "12345");