package tasks;
import java.util.Scanner;

/**
 * Реализуйте метод, который запрашивает у пользователя ввод
 * дробного числа (типа float), и возвращает введенное значение.
 * Ввод текста вместо числа не должно приводить к падению приложения,
 * вместо этого, необходимо повторно запросить у пользователя ввод данных.
 */
public class task4 {
    public static float getFloat() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Введите число с плавающей запятой: ");
            return Float.parseFloat(scanner.nextLine());
        }
        catch (NumberFormatException e ) {
            System.out.println("Введено неверное число");
            return getFloat();
        }

    }
}
