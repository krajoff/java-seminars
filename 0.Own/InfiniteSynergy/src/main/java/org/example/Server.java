package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private static final Logger LOG = LoggerFactory.getLogger(Server.class);
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public void start() {
        try {
            server = new ServerSocket(8000);
            System.out.println("Server started");
            clientSocket = server.accept();
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String word = in.readLine();
            System.out.println(word);
            out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
            out.flush();
        } catch (IOException e) {
            LOG.debug(e.getMessage());
            System.err.println(e);
        }
    }

    public void stop() {
        try {
            clientSocket.close();
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            LOG.debug(e.getMessage());
            System.err.println(e);
        }
    }
}

