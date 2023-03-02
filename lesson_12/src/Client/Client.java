package Client;

import Core.UI.UserInterfaceView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 8866)) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            UserInterfaceView.runUI(dataInputStream, dataOutputStream);
            while (true) {
                System.out.print("Введите уравнение: ");
                String request = scanner.nextLine();
                dataOutputStream.writeUTF(request);
                if (request.equals("end")) break;
                System.out.println("Решение уравнения: " + dataInputStream.readUTF());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}