package Core.Server;

import Core.Log.SpecialFilter;
import Core.Log.SpecialFormatter;
import Core.Log.SpecialHandler;
import Core.Model.Calculation;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;


public class Server {
    static Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) {
        logger.setLevel(Level.FINE);
        logger.addHandler(new SpecialHandler());
        try (ServerSocket serverSocket = new ServerSocket(8866)) {

            Handler fileHandler = new FileHandler("logger.log", 2000, 1);
            fileHandler.setFormatter(new SpecialFormatter());
            fileHandler.setFilter(new SpecialFilter());
            logger.addHandler(fileHandler);
            logger.log(Level.INFO, "Сервер запущен, ожидаем уравнение...");

            Socket socket = serverSocket.accept();
            logger.log(Level.INFO, "Клиент подключился к серверу...");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            while (true) {
                String clientRequest = dataInputStream.readUTF();
                if (clientRequest.equals("end")) break;
                logger.log(Level.INFO, "На сервер отправлено уравнение: " + clientRequest);
                dataOutputStream.writeUTF(new Calculation().getSolution(clientRequest));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
