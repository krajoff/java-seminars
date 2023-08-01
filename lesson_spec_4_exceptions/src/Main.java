public class Main {
    public static void main(String[] args) {
        String[][] arrayString = {{"1", "2", "3", "4"},
                {"10", "0", "10", "10"}, {"0", "1", "1", "2"},
                {"0", "0", "0", "1"}};
        try {
            ArrayString.sizeCheck(arrayString);
        } catch (MyArraySizeException e) {
            System.out.print("Вы используете неверную размерность\n" );
        }

        try {
            int sum = ArrayString.getSum(arrayString);
            System.out.printf("Сумма = %s\n", sum);
        } catch (MyArrayDataException e) {
            System.out.printf("Вы используете неверный тип данных в строке %s, столбеце %s\n",
                    e.getRow() + 1, e.getColumn() + 1);
        }
    }
}
