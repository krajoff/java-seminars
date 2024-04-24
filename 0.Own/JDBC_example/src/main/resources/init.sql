CREATE SCHEMA IF NOT EXISTS test;
USE test;

DROP TABLE IF EXISTS users;
CREATE TABLE users(id BIGSERIAL PRIMARY KEY, login VARCHAR(80), email VARCHAR(80), password VARCHAR(50));

INSERT INTO users(Login, Email, PasswordHash) VALUES ('Me', 'me@me.ru', '12345');
