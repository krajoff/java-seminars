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