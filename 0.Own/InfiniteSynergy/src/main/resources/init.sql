CREATE SCHEMA IF NOT EXISTS banking;
USE banking;

DROP TABLE IF EXISTS users;
CREATE TABLE users(id BIGINT SERIAL PRIMARY KEY, login VARCHAR(80),
email VARCHAR(80), password VARCHAR(50),);
INSERT INTO users(login, email, passwordHash) VALUES ('Me', 'me@me.ru', '12345');
