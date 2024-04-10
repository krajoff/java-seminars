class Solution4 {
    int[][] result;

    public int[][] FillArray(int size) {
        result = new int[size][size];
        if (size % 2 != 0) {
            throw new RuntimeException("Не допустимый размер матрицы. " +
                    "Введите четное число");
        }
        int top = 0,
                bottom = size - 1,
                left = 0,
                right = size - 1;
        while (true) {
            if (left > right)
                break;
            // fill top row
            for (int i = left; i <= right; i++)
                result[top][i] = size / 2 - top - 1;
            top++;
            if (top > bottom)
                break;
            // fill right column
            for (int i = top; i <= bottom; i++)
                result[i][right] = right - size / 2;
            right--;
            if (left > right)
                break;
            // fill bottom row
            for (int i = right; i >= left; i--)
                result[bottom][i] = bottom - size / 2;
            bottom--;
            if (top > bottom)
                break;
            // fill left column
            for (int i = bottom; i >= top; i--)
                result[i][left] = size / 2 - left - 1;
            left++;
        }
        return result;
    }

    public void printArray(int[][] array) {
        for (int[] row : array) {
            for (int el : row)
                System.out.print(el + " ");
            System.out.println();
        }
    }
}

public class Task4 {
    public static void main(String[] args) {
        Solution4 solution4 = new Solution4();
        solution4.printArray(solution4.FillArray(10));
    }

}
