import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ex4("2? + ?5 = 69");
    }

    // Вычислить n-ое треугольного число (сумма чисел от 1 до n)
    static void ex0() {
        System.out.println("Введите целое число: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        number = scanner.nextInt();
        int result = number * (number + 1) / 2;
        System.out.printf("Треугольное число для %d = %d", number, result);
    }

    // Вычислить n! (произведение чисел от 1 до n)
    static void ex1() {
        System.out.println("Введите целое число: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        int result = 1;
        number = scanner.nextInt();
        for (int i = 1; i < number + 1; i++) {
            result *= i;
        }
        System.out.printf("%d! = %d", number, result);
    }

    // Вывести все простые числа от 1 до 1000
    static void ex2() {
        System.out.println("Простые числа от 1 до 1000: 1 ");
        boolean flag;
        for (int i = 2; i < 1000; i++) {
            flag = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) System.out.printf("%d ", i);
        }
    }

    /*
    Реализовать простой калькулятор ("введите первое число"...
    "Введите второе число"... "укажите операцию, которую надо
    выполнить с этими числами"... "ответ: ...")
    */
    static void ex3() {
        System.out.print("Введите первое число: ");
        Scanner scan = new Scanner(System.in);
        int num1 = scan.nextInt();
        System.out.print("Введите второе число: ");
        int num2 = scan.nextInt();
        System.out.print("Укажите операцию: ");
        char opt = scan.next().charAt(0);
        int result = -1;
        switch (opt) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            case '*':
                result = num1 * num2;
                break;
        }
        System.out.printf("%d %s %d = %d", num1, opt, num2, result);
    }

    /*
    Задано уравнение вида q + w = e, q, w, e >= 0. Некоторые цифры могут
    быть заменены знаком вопроса, например 2? + ?5 = 69. Требуется восстановить
    выражение до верного равенства. Предложить хотя бы одно решение или сообщить, что его нет.
    */
    static String ex4(String str) {
        String[] eq = str.split(" ");
        String result = null;
        System.out.println(Arrays.toString(eq));

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){

                char[] chars = str.toCharArray();

            }
        }

        return result;
    }
}

