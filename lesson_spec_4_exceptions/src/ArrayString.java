public class ArrayString {

    static void sizeCheck(String[][] strings) throws MyArraySizeException {
        if (strings.length != 4) {
            throw new MyArraySizeException(
                    "Вы используете неверную размерность",
                    strings.length);
        }
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].length != 4) {
                throw new MyArraySizeException(
                        "Вы используете неверную размерность",
                        strings.length);
            }
        }
    }

    static int getSum(String[][] arrayString) throws MyArrayDataException {
        int sum = 0;
        for (int i = 0; i < arrayString.length; i++) {
            for (int j = 0; j < arrayString[i].length; j++) {
                try {
                    sum = sum + Integer.parseInt(arrayString[i][j]);
                } catch (RuntimeException e) {
                    throw new MyArrayDataException(
                            "Вы используете неверный тип данных", i, j);
                }
            }
        }
        return sum;
    }
}
