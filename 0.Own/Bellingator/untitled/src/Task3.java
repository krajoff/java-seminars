import java.util.ArrayList;
import java.util.Arrays;

/**
 * Дан массив NxN. Напишите программу на Java которая находит
 * минимальный элемент диагонали на картинке, без учёта элемента
 * пересечения диагоналей.
 */
class Solution3 {
    public int minElementTofDiagonal(int[][] array) {
        int result = Integer.MAX_VALUE;
        int size = array.length - 1;
        int temp;
        int restriction = array.length / 2;
        if (array.length % 2 == 0) {
            for (int i = 0; i < array.length; i++) {
                temp = array[size - i][i];
                if (temp < result) result = temp;
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                temp = array[size - i][i];
                if (temp < result
                        && i != restriction) {
                    result = temp;
                }
            }
        }
        return result;
    }

}

public class Task3 {
    public static void main(String[] args) {
        Solution3 solution3 = new Solution3();
        int[][] array1 = {{1, 2, 3, 4, 5},
                {5, 7, 9, 2, 1},
                {0, 9, 1, 8, 7},
                {6, 3, 6, 6, 6},
                {99, 100, -2, 3, 1}};
        System.out.println(solution3.minElementTofDiagonal(array1));
        int[][] array2 = {{1, 2, 3, 4, 5, 1},
                {5, 7, 9, 2, 1, 0},
                {0, 9, 1, 8, 7, 5},
                {6, 3, 6, 6, 6, -5},
                {99, 100, -2, 3, 1, -4},
                {99, 100, -2, 3, 1, 8}};
        System.out.println(solution3.minElementTofDiagonal(array2));
    }
}
