CREATE SCHEMA IF NOT EXISTS wallets;

DROP TABLE IF EXISTS wallets.users;
CREATE TABLE banking.users
(
    id BIGSERIAL PRIMARY KEY,
    login VARCHAR(80) NOT NULL,
    password VARCHAR(256) NOT NULL,
    balance DOUBLE PRECISION DEFAULT 0
);

INSERT INTO banking.users (login, password, balance)
VALUES ('Nikolay', '12345', 0);
INSERT INTO banking.users (login, password, balance)
VALUES ('Peter', '00000', 0);

DROP TABLE IF EXISTS banking.tokens;
CREATE TABLE banking.tokens
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGSERIAL NOT NULL,
    token TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES banking.users (id)
);