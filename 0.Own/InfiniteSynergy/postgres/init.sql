CREATE SCHEMA IF NOT EXISTS banking;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(id BIGSERIAL PRIMARY KEY, login VARCHAR(80),
password VARCHAR(50), balance DOUBLE PRECISION);

INSERT INTO users (login, password, balance) VALUES ('Nikolay', '12345',  0);
INSERT INTO users (login, password, balance) VALUES ('Peter', '00000',  0);
