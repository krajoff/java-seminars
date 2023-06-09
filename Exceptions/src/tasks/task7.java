package tasks;

import java.util.Scanner;

/**
 * Разработайте программу, которая выбросит Exception, когда пользователь
 * вводит пустую строку. Пользователю должно показаться сообщение, что
 * пустые строки вводить нельзя
 */
public class task7 {
    public static void message() {
        System.out.print("Введите данные: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            System.out.println("Всё хорошо!");
        } else {
            System.err.println("Ввод пустых строк запрещен");
        }
    }
}
