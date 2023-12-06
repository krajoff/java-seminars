package hometask1;

import java.util.Arrays;
import java.util.List;

/**
 * Напишите программу, которая использует Stream API для обработки
 * списка чисел. Программа должна вывести на экран среднее значение
 * всех четных чисел в списке.
 * 2. Дополнительная задча: Переработать метод балансировки корзины
 * товаров cardBalancing() с использованием Stream API
 */

public class Program {
    public static void main(String[] args) {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 0);
        Integer sum = array.
                stream().
                filter(a -> a % 2 == 0).
                mapToInt(Integer::byteValue).
                sum();
        long count = array.
                stream().
                filter(a -> a % 2 == 0).count();
        System.out.println(sum / count);
    }
}
