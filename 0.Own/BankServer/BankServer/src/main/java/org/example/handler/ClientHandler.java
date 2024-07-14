package org.example.handler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.example.models.HttpRequest;
import org.example.repository.UserRepository;
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
    private static final Logger logger = LoggerFactory
            .getLogger(ClientHandler.class);


    public ClientHandler(Socket socket, UserRepository userRepository) {
        this.socket = socket;
        this.userService = new UserService(userRepository);
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
            int contentLength = 0;

            while ((line = in.readLine()) != null && !line.isEmpty()) {
                headers.append(line).append("\n");
                if (line.startsWith("POST") || line.startsWith("GET")) {
                    String[] requestLineParts = line.split(" ");
                    method = requestLineParts[0];
                    path = requestLineParts[1];
                }
                if (line.startsWith("Content-Length:")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
            }

            if (contentLength > 0) {
                char[] bodyChars = new char[contentLength];
                in.read(bodyChars, 0, contentLength);
                body.append(bodyChars);
            }

            HttpRequest httpRequest = new HttpRequest(method,
                    path.toLowerCase(), body.toString());
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
                            break;
                    }
                    break;
                case "GET":
                    switch (path) {
                        case "/money":
                            handleSignin(jsonRequest, out);
                            break;
                        case "/something":
                            break;
                    }
            }
        } catch (JSONException e) {
            logger.error("Invalid JSON format", e);
            out.println("Invalid JSON format: " + e.getMessage());
        }
    }

    private void handleSignup(JSONObject jsonRequest, PrintWriter out) {
        try {
            String login = jsonRequest.getString("login");
            String password = jsonRequest.getString("password");
            String hashedPassword = hashPassword(password);
            userService.registrateUser(login, hashedPassword);
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
            out.println("HTTP/1.1 200 OK\r\nContent-Type: application/json\r\n\r\n");
            out.println(new JSONObject().put("token", token).toString());
            logger.info(String.format("User %s is successfully signed in", login));
        } catch (JSONException e) {
            logger.error("Invalid JSON in signin", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("No such algorithm in signin", e);
        } catch (SQLException e) {
            logger.error("Invalid SQL in signup", e);
        }
    }

    private void handleTransfer(BufferedReader in, PrintWriter out) throws IOException, SQLException, JSONException {
        String authorization = in.readLine().split(" ")[1];

        String login = JwtUtil.validateToken(authorization);

        if (login == null) {
            out.println("HTTP/1.1 401 Unauthorized");
            return;
        }

        StringBuilder body = new StringBuilder();
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            body.append(line);
        }

        JSONObject json = new JSONObject(body.toString());
        String to = json.getString("to");
        double amount = json.getDouble("amount");

        if (userService.transferMoney(login, to, amount)) {
            out.println("HTTP/1.1 200 OK");
            LoggerUtil.log(String.format("User \"%s\" sent %f$ to user \"%s\"", login, amount, to));
        } else {
            out.println("HTTP/1.1 400 Bad Request");
        }
    }

    private void handleBalance(BufferedReader in, PrintWriter out) throws IOException, SQLException, JSONException {
        String authorization = in.readLine().split(" ")[1];

        String login = JwtUtil.validateToken(authorization);

        if (login == null) {
            out.println("HTTP/1.1 401 Unauthorized");
            return;
        }

        double balance = userService.getBalance(login);
        if (balance >= 0) {
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: application/json");
            out.println();
            out.println(new JSONObject().put("balance", balance).toString());
            LoggerUtil.log(String.format("User \"%s\" balance is %f$", login, balance));
        } else {
            out.println("HTTP/1.1 400 Bad Request");
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

}

