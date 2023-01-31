import java.nio.charset.Charset;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ex0(10);
        ex1(10);
        ex2(2);
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
        float average = (float) sum / number;
        System.out.println("Array: " + list);
        System.out.println("Maximum of array: " + Collections.max(list));
        System.out.println("Minimum of array: " + Collections.min(list));
        System.out.printf("Average of array: %.2f", average);
        System.out.println();
    }

    static void ex2(int number) {
        Random random = new Random();
        List<Integer> list = new ArrayList();
        for (int i = 0; i < number; i++) {
            list.add(random.nextInt(10));
        }
        System.out.println("Random array: " + list);
       // List<Integer> sortlist = new ArrayList(number);
        //System.out.println("Random array: " + sortlist);
        //for (int i = 0; i < number; i++)
        System.out.println("Sorted array: " + mergeArrays(list,list));

    }

    static List<Integer> mergeArrays(List<Integer> array1, List<Integer> array2) {
        List<Integer> mergedArray = new ArrayList();
        int j = 0;
        int k = 0;
        for (int i = 0; i < (array1.size() + array2.size()); i++) {
            if (array1.get(j) > array2.get(k)) {
                mergedArray.add(array2.get(k));
                k++;
            }
            else {
                if (k>)
                mergedArray.add(array1.get(j));
                j++;
            }
        }
        return mergedArray;
    }
}
