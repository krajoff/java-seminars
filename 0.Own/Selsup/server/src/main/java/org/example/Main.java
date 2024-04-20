package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            var isr = new InputStreamReader(t.getRequestBody(), StandardCharsets.UTF_8);
            var br = new BufferedReader(isr);
            var json = br.readLine();
            var json_ = json.replace("109 ", ""); // удаление 109

            // Десериализация из json
            var reader = new StringReader(json_);
            var objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            Request request = objectMapper.readValue(reader, Request.class);
            Document document = request.document;
            String signature = request.signature;
            System.out.println("Request_document: "+ document);
            System.out.println("Request_signature: "+ signature);

            // Сериализация в json
            var writer = new StringWriter();
            objectMapper.writeValue(writer, document);
            String response = writer.toString();
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}