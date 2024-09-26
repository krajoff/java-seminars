package org.example;

/**
 *
 */

public class MissingNumberFinder {

    public static int findMissingNumber(int[] nums) {

        if (nums == null)
            throw new IllegalArgumentException
                    ("Массив не должен быть null");
        if (nums.length == 1)
            return -1;

        int min = nums[0];
        int max = nums[0];
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
            if (num < min)
                min = num;
            if (num > max)
                max = num;
        }

        int expectedSum = (max * (max + 1)) / 2 - ((min - 1) * min) / 2;
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        testFindMissingNumber();
        System.out.println("Тесты пройдены");
    }

    public static void testFindMissingNumber() {

        int[] input1 = {5, 0, 1, 3, 2};
        assert findMissingNumber(input1) == 4 : "Тест 1 провален";

        int[] input2 = {7, 9, 10, 11, 12};
        assert findMissingNumber(input2) == 8 : "Тест 2 провален";

        int[] input3 = {2};
        assert findMissingNumber(input3) == -1 : "Тест 3 провален";

        int[] input4 = {4, 5, 6, 7};
        assert findMissingNumber(input4) == 8 : "Тест 4 провален";

        int[] input5 = {10, 12, 13, 11, 15};
        assert findMissingNumber(input5) == 14 : "Тест 5 провален";
    }
}