package tasks;

/**
 * Реализуйте 3 метода, чтобы в каждом из них получить разные стандартные для Java исключения;
 */

public class task1 {
    public static int integerMod(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Divided by 0 is prohibited");
        } else {
            return a % b;
        }
    }

    public static int valueArray(int index, int[] list) {
        if (list.length < index) {
            throw new IndexOutOfBoundsException
                    (String.format("Index %s is out from the bound of array", index));
        } else {
            return list[index];
        }
    }

    public static long sumInteger(long a, long b) {
        if (a >= Integer.MAX_VALUE || b >= Integer.MAX_VALUE || a + b >= Integer.MAX_VALUE)
            throw new RuntimeException("Values are extremely huge");
        else return a + b;
    }
}
