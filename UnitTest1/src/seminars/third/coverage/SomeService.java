package seminars.third.coverage;

public class SomeService {

    /**
     * 3.1. Метод возвращает Fizz для чисел кратных 3,
     * Buzz для кратных 5, и FizzBuzz для кратных 3 и 5 одновременно
     */
    public String fizzBuzz(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        }
        if (i % 3 == 0) {
            return "Fizz";
        }
        if (i % 5 == 0) {
            return "Buzz";
        }
        return null;
    }

    /**
     * 3.2. Метод возвращает true для массивов, которые начинаются
     * или заканчиваются 6, и false - если 6 нет ни в начале ни в конце массива
     */
    public boolean firstLast6(int[] nums) {
        return false;
    }


    /**
     * 3.3. Метод подсчета скидки
     */
    public double calculatingDiscount(double purchaseAmount, int discountAmount) {

        return purchaseAmount;
    }


    /**
     * 3.4. Метод принимает на вход 3 числа (int a, b, c). Нужно вернуть их сумму.
     * Однако, если одно из значений равно 13,то оно не учитывается в сумме.
     * Так, например, если b равно 13, то считается сумма только a и c.
     */

    /**
     * Напишите тесты, покрывающие на 100% метод evenOddNumber, который проверяет,
     * является ли переданное число четным или нечетным. (код приложен в презентации)
     */
    public boolean evenOddNumber(int n) {
        if (n % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     Разработайте и протестируйте метод numberInInterval, который проверяет,
     попадает ли переданное число в интервал (25;100). (код приложен в презентации)
     */
    public boolean numberInInterval(int n) {
        return n > 25 && n < 100;
    }
}