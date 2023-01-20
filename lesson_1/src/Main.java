import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ex1();
    }

    // Вычислить n-ое треугольного число (сумма чисел от 1 до n)
    static void ex0() {
        System.out.printf("Введите целое число: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        number = scanner.nextInt();
        int result = number * (number + 1) / 2;
        System.out.printf("Треугольное число для %d = %d", number, result);
    }

    // Вычислить n! (произведение чисел от 1 до n)
    static void ex1() {
        System.out.printf("Введите целое число: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        int result = 1;
        number = scanner.nextInt();
        for (int i = 1; i < number + 1; i++) {
            result *= i;
        }
        System.out.printf("%d! = %d", number, result);
    }
}

