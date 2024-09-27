package org.example;
/**
 * Write a function as described below and test scenarios to test its validity.
 * Details
 * Write a function that finds missing number in array:
 * Given array of sequent numbers 0,1,2,3...N with missing member
 * Function finds a first missing number occurrence in the sequence.
 * Example:
 * Input: [5,0,1,3,2]
 * Output: 4
 * Input: [7, 9, 10, 11, 12]
 * Output: 8
 * Implement the function + tests and explain your thinking and assumptions as comments.
 * You can send the solution as a link to github (or similar)
 * Note:
 * Think of every possible scenario to test your function including corner cases.
 * Solutions in email will not be accepted.
 */


/**
 * Класс {@code MissingNumberFinder} предоставляет метод для поиска первого пропущенного числа
 * в последовательности целых положительных чисел. Предполагается, что последовательность состоит
 * из последовательных чисел, начинающихся с любого числа. Данное решение находит пропущенное число
 * без сортировки массива за один проход, т.е. О(N).
 * <p>
 * Класс обрабатывает случаи, когда входной массив содержит только одно число, является {@code null},
 * или когда пропущенное число является первым или последним в последовательности. Если пропущенных
 * значений нет, то возвращается -1, иначе возвращается пропущенное число.
 * </p>
 * <p>
 * Пример использования:
 * <pre>
 *     int[] input = {5, 0, 1, 3, 2};
 *     int missing = MissingNumberFinder.findMissingNumber(input);
 *     // missing будет равно 4
 * </pre>
 * </p>
 */
public class MissingNumberFinder {

    public static long findMissingNumber(long[] nums) {

        if (nums == null)
            throw new IllegalArgumentException("Массив не должен быть null");
        if (nums.length == 1)
            return -1;

        long min = nums[0];
        long max = nums[0];
        long actualSum = 0;

        for (var num : nums) {
            actualSum += num;
            if (num < min)
                min = num;
            if (num > max)
                max = num;
        }

        long expectedSum = (max * (max + 1)) / 2 - ((min - 1) * min) / 2;
        long delta = expectedSum - actualSum;
        return delta == 0 ? -1 : delta;
    }

    public static void main(String[] args) {
        testFindMissingNumber();
        System.out.println("Тесты пройдены");
    }

    public static void testFindMissingNumber() {

        long[] input1 = {5, 0, 1, 3, 2};
        assert findMissingNumber(input1) == 4 : "Тест 1 провален";

        long[] input2 = {7, 9, 10, 11, 12};
        assert findMissingNumber(input2) == 8 : "Тест 2 провален";

        long[] input3 = {2};
        assert findMissingNumber(input3) == -1 : "Тест 3 провален";

        long[] input4 = {4, 5, 6, 7};
        assert findMissingNumber(input4) == -1 : "Тест 4 провален";

        long[] input5 = {10, 12, 13, 11, 15};
        assert findMissingNumber(input5) == 14 : "Тест 5 провален";

        long[] input6 = {Integer.MAX_VALUE, Integer.MAX_VALUE - 1,
                Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 4};
        assert findMissingNumber(input6) == Integer.MAX_VALUE - 3 :
                "Тест 6 провален";

    }
}