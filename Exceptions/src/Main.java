import tasks.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Остаток от деления: " + task1.integerMod(4, 1));
        System.out.println("Значение в массиве: " + task1.valueArray(6, new int[]{0,2,3,5,6,8,4}));
        System.out.println("Сумма чисел: " + task1.sumInteger(2_147_483_636L, 10L));
        int[] arg0 = new int[]{1, 2, 3};
        int[] arg1 = new int[]{0, 5, 1};
        int[] d2a = task3.difference2arrays(arg0, arg1);
        System.out.println("Новый массив: " + Arrays.toString(d2a));
        //System.out.println(task2.sum2d(new String[][]{{"0", "3"},{"0", "3"},{"1", "3"},{"0", "3"},{"1", "3"}}));
    }
}
