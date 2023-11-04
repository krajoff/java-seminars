package seminars.sixth;

import seminars.sixth.array.ArrayCalculation;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Double> array1 = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0, 6.0);
        ArrayCalculation arrayAverage = new ArrayCalculation();
        System.out.println(arrayAverage.average(array1));
    }
}
