package seminars.first.Calculator;

import java.io.IOException;

import static java.lang.Math.sqrt;

public class Calculator {
    public static int calculation(int firstOperand, int secondOperand, char operator) {
        int result;

        switch (operator) {
            case '+':
                result = firstOperand + secondOperand;
                break;
            case '-':
                result = firstOperand - secondOperand;
                break;
            case '*':
                result = firstOperand * secondOperand;
                break;
            case '/':
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                    break;
                } else {
                    throw new ArithmeticException("Division by zero is not possible");
                }
            default:
                throw new IllegalStateException("Unexpected value operator: " + operator);
        }
        return result;
    }

    // HW1.1: Придумайте и опишите (можно в псевдокоде) функцию извлечения корня и
    // необходимые проверки для него используя граничные случаи
    public static double squareRootExtraction(double num) {
        //  0
        //  Отрицательные числа
        //  Дробные значения корней
        //  Целые
        if (num < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of a negative number");
        } else if (num == 0) {
            System.out.println(0);
        } else if (num > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Number is out of maximum value");
        }
        return sqrt(num);
    }

    // Нужно написать в калькуляторе метод вычисления суммы покупки
    // со скидкой и проверить его, используя AssertJ
    // Примерная сигнатура и тело метода:
    /**
     * Возвращает сумму покупки со скидкой
     * purchaseAmount - сумма покупки в
     * discountAmount - размер скидки в процентах;
     **/
    public static double calculatingDiscount(double purchaseAmount, int discountAmount) {
        if (discountAmount < 0 || discountAmount > 100) {
            throw new IllegalArgumentException("Wrong discount amount " + discountAmount);
        }
        if (purchaseAmount <= 0) {
            throw new IllegalArgumentException("Purchase amount is less nil " + purchaseAmount);
        }
        return purchaseAmount * (100 - discountAmount) * .01;
    }
}