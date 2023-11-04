package Core.UI;

import Core.Model.Controller;

import java.io.*;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();
    public static void runUI(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите уравнение: ");
            String request = scanner.nextLine();
            dataOutputStream.writeUTF(request);
            if (request.equals("end")) break;
            System.out.println("Решение уравнения: " + dataInputStream.readUTF());
        }
    }
}
