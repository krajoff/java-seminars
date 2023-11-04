package tasks;

/**
 * Реализуйте метод, принимающий в качестве аргументов два целочисленных массива,
 * и возвращает новый массив, каждый элемент которого равен разности элементов
 * двух входящих массивов в той же ячейке. Если длины массивов не равны, необходимо
 * как-то оповестить пользователя.
 */
public class task3 {
    public static int[] difference2arrays(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new RuntimeException("Arrays dimensions are not equal");
        } else {
            int[] result = new int[a.length];
            for (int i = 0; i < a.length; i++)
                result[i] = a[i] - b[i];
            return result;
        }
    }
}
