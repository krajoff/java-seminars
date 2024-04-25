package org.example;

import org.example.util.CRUDUser;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        //System.out.println(CRUDUser.findAllUser());
        Server server = new Server();
        server.run(8000);

    }
}
