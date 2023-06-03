package tasks;
/**
* Посмотрите на код, и подумайте сколько разных типов исключений вы тут сможете получить?
*/
 public class task2 {
    public static int sum2d(String[][] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 5; j++) {
                int val = Integer.parseInt(arr[i][j]);
                sum += val;
            }
        }
        return sum;
    }
}
/*
 * 1. Несоответствие входных типов данных при parseInt
 * 2. Выход за границы индексов массива при условии j < 5
 * 3. Выход за значение Integer.MAX_VALUE или Integer.MIN_VALUE
 */