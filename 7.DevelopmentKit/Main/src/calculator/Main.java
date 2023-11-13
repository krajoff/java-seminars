package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    /*
    1. Написать класс Калькулятор (необобщенный), который содержит обобщенные
    статические методы: sum(), multiply(), divide(), subtract(). Параметры этих
    методов – два числа разного типа, над которыми должна быть произведена операция.

    2. Напишите обобщенный метод compareArrays(), который принимает два массива и
    возвращает true, если они одинаковые, и false в противном случае. Массивы могут
    быть любого типа данных, но должны иметь одинаковую длину и содержать элементы одного типа.

    3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
    Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из
    составляющих пары, а также переопределение метода toString(), возвращающее строковое представление пары.
    */
    public static void main(String[] args) {

        System.out.println(Calculator.sum(3.1d, 4f));

        List<Integer> a = new ArrayList<>(Arrays.asList(1, 2));
        List<Integer> b = new ArrayList<>(Arrays.asList(1, 2));
        List<Double> c = new ArrayList<>(Arrays.asList(1.0, 2.0));
        System.out.println(new ArrayProсedure().compareArrays(a, b));
        System.out.println(new ArrayProсedure().compareArrays(a, c));

        Pair pair = new Pair<Integer, Float>(5, 6f);
        System.out.println(pair);
    }
}
