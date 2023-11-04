package fourth;

import java.util.Arrays;
import java.util.Random;

/*
Написать скрипт для расчета корреляции Пирсона между двумя случайными величинами
(двумя массивами). Можете использовать любую парадигму, но рекомендую использовать
функциональную, т.к. в этом примере она значительно упростит вам жизнь
 */
public class Main {
    public static void main(String[] args) {
        int number = 100;
        Double[] array1 = rndArray(number);
        Double[] array2 = rndArray(number);
        System.out.println("Pearson ratio = " + pearsonRatio(array1, array2));
    }

    public static Double[] rndArray(int number) {
        Double[] array = new Double[number];
        for (int i = 0; i < number; i++) {
            array[i] = Math.random();
        }
        return array;
    }

    public static Double avrArray(Double[] array) {
        Double result = 0.0;
        for (Double value : array)
            result += value;
        return result;
    }

    public static Double pearsonRatio(Double[] array1, Double[] array2) {
        Double avrArray1 = avrArray(array1);
        Double avrArray2 = avrArray(array2);
        double k = 0.0;
        double z = 0.0;
        for (int i = 0; i < array1.length; i++) {
            k += (array1[i] - avrArray1) * (array2[i] - avrArray2);
            z += Math.pow(array1[i] - avrArray1, 2) *
                    Math.pow(array2[i] - avrArray2, 2);
        }
        return k / Math.sqrt(z);
    }


}
