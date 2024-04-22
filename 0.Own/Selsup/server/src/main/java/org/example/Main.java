package org.example;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;


public class Main {
    static int requestLimit = 2;
    static TimeUnit timeUnit = TimeUnit.HOURS;
    static CrptApi crptApi;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new CustomHandler());
        crptApi = new CrptApi(timeUnit, requestLimit);
        server.setExecutor(null);
        server.start();

    }


}