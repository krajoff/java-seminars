import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ex0();
        ex1();
        ex2();
        ex3();
    }

    // Вычислить n-ое треугольного число (сумма чисел от 1 до n)
    static void ex0() {
        System.out.print("Input integer number: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        number = scanner.nextInt();
        int result = number * (number + 1) / 2;
        System.out.printf("For %d = %d", number, result);
        System.out.println();
    }

    // Вычислить n! (произведение чисел от 1 до n)
    static void ex1() {
        System.out.print("Input integer number: ");
        Scanner scanner = new Scanner(System.in);
        int number;
        int result = 1;
        number = scanner.nextInt();
        for (int i = 1; i < number + 1; i++) {
            result *= i;
        }
        System.out.printf("%d! = %d", number, result);
        System.out.println();
    }

    // Вывести все простые числа от 1 до 1000
    static void ex2() {
        System.out.println("Prime number from 1 till 1000: 1 ");
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
        System.out.println();
    }

    /*
    Реализовать простой калькулятор ("введите первое число"...
    "Введите второе число"... "укажите операцию, которую надо
    выполнить с этими числами"... "ответ: ...")
    */
    static void ex3() {
        System.out.print("Input first number: ");
        Scanner scan = new Scanner(System.in);
        int num1 = scan.nextInt();
        System.out.print("Input second number: ");
        int num2 = scan.nextInt();
        System.out.print("Input operand: ");
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
}

