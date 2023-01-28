import java.nio.charset.Charset;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ex0(10);
        ex1(10);
    }

    // Пусть дан произвольный список целых чисел, удалить из него четные числа
    static void ex0(int number) {
        Random random = new Random();
        List<Integer> list = new ArrayList();
        for (int i = 0; i < number; i++) {
            list.add(random.nextInt(10));
        }
        System.out.println("Initial array: " + list);
        list.removeIf(x -> x % 2 == 0);
        System.out.println("New array: " + list);
    }

    // Задан целочисленный список ArrayList. Найти минимальное, максимальное и
    // среднее арифметическое из этого списка. Collections.max()
    static void ex1(int number) {
        Random random = new Random();
        List<Integer> list = new ArrayList();
        int sum = 0;
        for (int i = 0; i < number; i++) {
            list.add(random.nextInt(10));
            sum += list.get(i);
        }
        float average = (float)sum/number;
        System.out.println("Array: " + list);
        System.out.println("Maximum of array: " + Collections.max(list));
        System.out.println("Minimum of array: " + Collections.min(list));
        System.out.printf("Average of array: %.2f", average);
    }
}
