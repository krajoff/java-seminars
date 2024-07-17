package org.example;

import org.example.handler.ClientHandler;
import org.example.repository.TokenRepository;
import org.example.repository.UserRepository;
import org.example.service.TokenService;
import org.example.service.UserService;

import org.example.util.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
 * регистрациях новых пользователей, а также аутентификациях существующих, в отдельный файл в формате:
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

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Connection connection = setupDatabase();
            UserRepository userRepository = new UserRepository(connection);
            TokenRepository tokenRepository = new TokenRepository(connection);

            TokenService tokenService = new TokenService(tokenRepository);
            UserService userService = new UserService(userRepository, tokenService);

            LoggerUtil.logInfo("Banking server is running on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket, userService, tokenService).start();
            }
        } catch (IOException | SQLException e) {
            LoggerUtil.logError("Failed to start the BankingServer: ", e);
        }
    }

    private static Connection setupDatabase() throws SQLException {
        Properties props = new Properties();
        try (InputStream input = UserRepository.class
                .getClassLoader()
                .getResourceAsStream(("application.properties"))) {
            props.load(input);
        } catch (IOException e) {
            LoggerUtil.logError("No connection", e);
        }
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);
    }


}
