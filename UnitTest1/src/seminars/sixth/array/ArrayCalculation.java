package seminars.sixth.array;

import java.util.List;

public class ArrayCalculation {

    public double average(List<Double> list) {
        double res = 0;
        for (Double i : list) {
            res += i;
        }
        return res / list.size();
    }

    public void compare(List<Double> list1, List<Double> list2) {
        if (average(list1) > average(list2)) {
            System.out.println("Первый список имеет большее среднее значение");
        } else if (average(list1) < average(list2)) {
            System.out.println("Второй список имеет большее среднее значение");
        } else {
            System.out.println("Средние значения равны");
        }
    }
}
