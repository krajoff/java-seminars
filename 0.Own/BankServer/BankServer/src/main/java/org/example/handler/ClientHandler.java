package org.example.handler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.example.models.HttpRequest;
import org.example.models.Transaction;
import org.example.models.User;
import org.example.repository.TokenRepository;
import org.example.repository.UserRepository;
import org.example.server.TokenService;
import org.example.server.UserService;
import org.example.util.JwtUtil;
import org.example.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientHandler extends Thread {
    private Socket socket;
    private UserService userService;
    private TokenService tokenService;
    private static final Logger logger = LoggerFactory
            .getLogger(ClientHandler.class);


    public ClientHandler(Socket socket, UserService userService,
                         TokenService tokenService) {
        this.socket = socket;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter
                     (socket.getOutputStream(), true)) {

            String line;
            StringBuilder headers = new StringBuilder();
            StringBuilder body = new StringBuilder();
            String method = "";
            String path = "";
            String token = "";
            int contentLength = 0;

            while ((line = in.readLine()) != null && !line.isEmpty()) {
                headers.append(line).append("\n");
                if (line.startsWith("POST") || line.startsWith("GET")) {
                    String[] requestLineParts = line.split(" ");
                    method = requestLineParts[0];
                    path = requestLineParts[1];
                }
                if (line.startsWith("Content-Length:"))
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                if (line.startsWith("Authorization: Bearer"))
                    token = line.substring("Authorization: Bearer ".length()).trim();
            }

            if (contentLength > 0) {
                char[] bodyChars = new char[contentLength];
                in.read(bodyChars, 0, contentLength);
                body.append(bodyChars);
            }

            HttpRequest httpRequest = new HttpRequest(method,
                    path.toLowerCase(), body.toString(), token);
            logger.info("Request: " + httpRequest);
            handleRequest(httpRequest, out);

        } catch (Exception e) {
            logger.error("Error handling client request", e);
        }
    }

    private int getContentLength(String headers) {
        for (String header : headers.split("\n")) {
            if (header.startsWith("Content-Length")) {
                return Integer.parseInt(header.split(":")[1].trim());
            }
        }
        return 0;
    }

    private void handleRequest(HttpRequest httpRequest, PrintWriter out) {
        try {
            JSONObject jsonRequest = new JSONObject(httpRequest.getBody());
            String method = httpRequest.getMethod();
            String path = httpRequest.getPath();
            String token = httpRequest.getToken();
            System.out.println(token);
            switch (method) {
                case "POST":
                    switch (path) {
                        case "/signup":
                            handleSignup(jsonRequest, out);
                            break;
                        case "/signin":
                            handleSignin(jsonRequest, out);
                            break;
                        case "/money":
                            handleTransfer(httpRequest, out);
                            break;
                    }
                    break;
                case "GET":
                    switch (path) {
                        case "/money":
                            handleBalance(token, out);
                            break;
                        case "/something":
                            break;
                    }
            }
        } catch (JSONException e) {
            logger.error("Invalid JSON format", e);
            // out.println("Invalid JSON format: " + e.getMessage());
        } catch (SQLException e) {
            logger.error("Invalid SQL", e);
        }
    }

    private void handleSignup(JSONObject jsonRequest, PrintWriter out) {
        try {
            String login = jsonRequest.getString("login");
            String password = jsonRequest.getString("password");
            String hashedPassword = hashPassword(password);
            if (userService.registrateUser(login, hashedPassword) != null)
                sendResponse(out, 200,
                        "User registered successfully");
            else
                sendResponse(out, 400, "User is already existed");
        } catch (JSONException e) {
            logger.error("Invalid JSON in signup", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm in signin", e);
        } catch (SQLException e) {
            logger.error("Invalid SQL in signup", e);
        }
    }

    private void handleSignin(JSONObject jsonRequest, PrintWriter out) {
        try {
            String login = jsonRequest.getString("login");
            String password = jsonRequest.getString("password");
            String hashedPassword = hashPassword(password);
            String token = userService.authenticateUser(login, hashedPassword);
            if (token != null) {
                sendResponse(out, 200,
                        new JSONObject().put("token", token).toString());
                logger.info(String.format("User %s is successfully signed in", login));
            } else {
                sendResponse(out, 401, "Invalid login or password");
                logger.info(String.format("User %s is not signed in", login));
            }
        } catch (JSONException e) {
            logger.error("Invalid JSON in signin", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm in signin", e);
        } catch (SQLException e) {
            logger.error("Invalid SQL in signup", e);
        }
    }

    private void handleBalance(String token, PrintWriter out) throws SQLException {
        long id = tokenService.validateToken(token);
        double balance;
        if (id != -1)
            balance = userService.getBalanceById(id);
        else {
            sendResponse(out, 401, "Unauthorized");
            return;
        }
        if (balance >= 0) {
            sendResponse(out, 200, new JSONObject().put("balance", balance).toString());
            logger.info(String.format("Balance is %f", balance));
        } else {
            sendResponse(out, 400, "Bad request");
        }
    }

    private void handleTransfer(HttpRequest httpRequest, PrintWriter out) throws SQLException, JSONException {
        long id = tokenService.validateToken(httpRequest.getToken());
        JSONObject jsonRequest = new JSONObject(httpRequest.getBody());
        Transaction transaction = Transaction.builder()
                .fromUser(userService.getUserById(id))
                .toUser(userService.getUserByLogin(jsonRequest.getString("to")))
                .amount(jsonRequest.getDouble("amount"))
                .timestamp(LocalDateTime.now())
                .build();
        userService.transferMoney(transaction);





            sendResponse(out, 400, "Bad request. Not enough money.");
        } else {
            sendResponse(out, 400, "Bad request. Non-exist user.");
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.
                digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private void sendResponse(PrintWriter out, int statusCode, String responseBody) {
        out.println("HTTP/1.1 " + statusCode + " " + getStatusMessage(statusCode));
        out.println("Content-Type: application/json");
        out.println("Content-Length: " + responseBody.length());
        out.println();
        out.println(responseBody);
    }

    private String getStatusMessage(int statusCode) {
        switch (statusCode) {
            case 200:
                return "OK";
            case 400:
                return "Bad Request";
            case 401:
                return "Unauthorized";
            case 404:
                return "Not Found";
            default:
                return "";
        }
    }

}

