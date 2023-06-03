package tasks;

/**
 * Реализуйте метод, принимающий в качестве аргумента целочисленный массив.
 * Если длина массива меньше некоторого заданного минимума, метод возвращает -1,
 * в качестве кода ошибки, иначе - длину массива.
 */
public class task0 {
    public static int checkArrayLength(int[] arg, int limit) {
        if (arg.length < limit) return -1;
        return arg.length;
    }
}