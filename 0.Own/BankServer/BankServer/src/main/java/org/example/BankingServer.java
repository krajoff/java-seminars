package org.example;

import org.example.handler.ClientHandler;
import org.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

/**
 * Необходимо создать банковский сервер, который будет позволять манипулировать денежными средствами.
 * ПО должно представлять сервер построенный согласно REST API.
 * Требования:
 * 1. Я, как пользователь, хочу иметь возможность регистрации и аутентификации.
 * POST <server_url>/signup
 * {
 * "login": "user_login"
 * "password": "user_pass"
 * }
 * <p>
 * POST <server_url>/signin
 * {
 * "login": "user_login"
 * "password": "user_pass"
 * }
 * <p>
 * 2. Я, как пользователь A, хочу иметь возможность отправить деньги пользователю B:
 * POST <server_url>/money
 * {
 * "to": "user_b_login"
 * "amount": 500
 * }
 * <p>
 * 3. Я, как пользователь, хочу иметь возможность увидеть свой текущий баланс с помощью запроса
 * GET <server_url>/money
 * <p>
 * • Каждый запрос должен сопровождаться и авторизоваться JWT токеном
 * • Пользователь может просмотреть только свой собственный баланс.
 * • Приложение должно записывать логи о всех проделанных операциях,
 *   регистрациях новых пользователей, а также аутентификациях существующих, в отдельный файл в формате:
 * <p>
 * <datetime>: <message>
 * Например:
 * 2024-04-15T07:41:39.996Z: User "A" was sent 500$ to the user "B"
 * 2024-04-15T07:41:39.996Z: User "A" balance is 300$
 * 2024-04-15T07:41:39.996Z: User "B" balance is 1300$
 * <p>
 * • Все данные приложение должно хранить в БД PostgreSql, для подключения к БД использовать JDBC
 * <p>
 * • Сервер должен быть реализован на базе ServerSocket
 */
public class BankingServer {
    private static final int PORT = 8080;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(BankingServer.class);

    public BankingServer() throws SQLException, InterruptedException {
        this.userRepository = new UserRepository();
    }

    public static void main(String[] args) throws IOException, SQLException, InterruptedException {
        BankingServer server = new BankingServer();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Server is listening on port " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, server.userRepository).start();
            }
        }
    }
}
