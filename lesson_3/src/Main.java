import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ex0(10);



        ex1();
    }

    // Пусть дан произвольный список целых чисел, удалить из него четные числа
    static void ex0(int number) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < number; i++) {
            list.add(i, (int)(Math.random() * 10));
        }
        System.out.println("Исходный массив: " + list);
        for (int i = 0; i < number; i++) {

            list.add(i, (int)(Math.random() * 10));
        }

    }

    // Задан целочисленный список ArrayList. Найти минимальное, максимальное и
    // среднее арифметическое из этого списка. Collections.max()
    static void ex1() {

    }
}
