package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private static final Logger LOG = LoggerFactory.getLogger(Server.class);
    private static Socket socket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public void start() {
        try {
            while (true) {
                server = new ServerSocket(8000);
                System.out.println("Server started");
                socket = server.accept();
                in = new BufferedReader(new InputStreamReader
                        (socket.getInputStream()));
                StringBuilder stringBuffer = new StringBuilder();

                String inLine;
                while ((inLine = in.readLine()) != null && !inLine.isEmpty()) {
                    stringBuffer.append(inLine);
                    stringBuffer.append("\r\n");
                }
                System.out.println(stringBuffer);

                out = new BufferedWriter(new OutputStreamWriter
                        (socket.getOutputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    out.write(Arrays.toString(line.getBytes()));
                }

                System.out.println(line);
                out.write("Привет, это Сервер! Подтверждаю, вы написали : " + line + "\n");
                out.flush();
            }

        } catch (IOException e) {
            LOG.debug(e.getMessage());
            System.err.println(e);
        }
    }

    public void run(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                Socket socket = serverSocket.accept();

                var in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                var stringBuffer = new StringBuilder();

                String inputLine;
                while ((inputLine = in.readLine()) != null && !inputLine.equals("")) {
                    stringBuffer.append(inputLine);
                    stringBuffer.append("\r\n");
                }
                System.out.println(stringBuffer);

                var outStream = socket.getOutputStream();
                var bufferedReader = new BufferedReader(
                        new StringReader("A Message from server."));

                try {
                    // Header should be ended with '\r\n' at each line
                    outStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                    //outStream.write("Main: OneServer 0.1\r\n".getBytes());
                    outStream.write("Content-Length: 22\r\n".getBytes()); // if text/plain the length is required
                    outStream.write("Content-Type: text/plain\r\n".getBytes());
                    //outStream.write("Connection: close\r\n".getBytes());

                    // An empty line is required after the header
                    outStream.write("\r\n".getBytes());

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        outStream.write(line.getBytes());
                    }

                    outStream.flush();
                    outStream.close();
                } catch (SocketException e) {
                    // Handle the case where client closed the connection while server was writing to it
                    socket.close();
                }

                bufferedReader.close();
                inBufferReader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            socket.close();
            in.close();
            out.close();
            server.close();
        } catch (IOException e) {
            LOG.debug(e.getMessage());
            System.err.println(e);
        }
    }
}

