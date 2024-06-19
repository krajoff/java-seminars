package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.nio.charset.StandardCharsets;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {

    private final Logger LOG = LoggerFactory.getLogger(Server.class);
    private Socket socket;
    private ServerSocket server;
    private BufferedReader in;
    private BufferedInputStream inb;
    //  private static BufferedWriter out;
    private DataInputStream ins = null;

    private byte[] BYTE_BUFFER = new byte[2048];

    public void ServerSide(int port) {
        try {
<<<<<<< HEAD
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
                while ((inputLine = in.readLine()) != null
                        && !inputLine.isEmpty()) {
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
                    outStream.write("Text: \r\n".getBytes());
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
                in.close();
=======
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            System.out.println("Client accepted");
            ins = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            String line = "";
            while (!line.equals("Over")) {
                try {
                    line = ins.readUTF();
                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println(i);
                }
>>>>>>> 267abb9d41f163ff6cb79d0d875f186524cc9370
            }
            System.out.println("Closing connection");
            socket.close();
            ins.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public void start() throws IOException {
        server = new ServerSocket(8080);

        try {

            System.out.println("Server started...");
            socket = server.accept();

            ins = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            char dataType = ins.readChar();
            int length = ins.readInt();
            in = new BufferedReader(new InputStreamReader
                    (socket.getInputStream()));
            StringBuilder stringBuffer = new StringBuilder();

            String inLine = "";
            while ((inLine = in.readLine()) != null && inLine.isEmpty()) {
                stringBuffer.append(inLine);
                stringBuffer.append("\r\n");
            }
            System.out.println(stringBuffer.length());
            System.out.println(dataType);
            System.out.println(length);

//
//                OutputStream out = socket.getOutputStream();
//                BufferedReader bufferedReader =
//                        new BufferedReader(
//                                new StringReader("A Message from server."));
//
//                // Header should be ended with '\r\n' at each line
//                out.write("HTTP/1.1 200 OK\r\n".getBytes());
//                //outStream.write("Main: OneServer 0.1\r\n".getBytes());
//                out.write("Content-Length: 22\r\n".getBytes());
//                // if text/plain the length is required
//                out.write("Content-Type: text/plain\r\n".getBytes());
//                //outStream.write("Connection: close\r\n".getBytes());
//                // An empty line is required after the header
//                out.write("\r\n".getBytes());
//                String line;
//                while ((line = bufferedReader.readLine()) != null) {
//                    out.write(line.getBytes());
//                }
//                out.flush();

        } catch (Exception e) {
            System.err.println(e);
        } finally {
            try {
                socket.close();
                in.close();
                ins.close();
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }
}