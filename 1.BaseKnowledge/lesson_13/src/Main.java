import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Размер массива: ");
        int size = scanner.nextInt();
        System.out.println("Элементы массива: ");
        int[] array = new int[size];
        for(int i=0; i<size; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println(Arrays.toString(sort(array)));


    }
    public static void heap(int[] array, int size, int idx) {
        int max = idx;
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        if (left < size && array[left] > array[max])
            max = left;
        if (right < size && array[right] > array[max])
            max = right;
        if (max != idx) {
            int temp = array[idx];
            array[idx] = array[max];
            array[max] = temp;

            heap(array, size, max);
        }
    }

    public static int[] sort(int[] array){
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heap(array, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heap(array, i ,0);
        }
        return array;
    }
}
