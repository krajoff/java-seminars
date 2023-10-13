package first;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(1, 2, 4, 5, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 4, 5, 3);
        System.out.println("Non-sorting array: " + list1);
        System.out.println("Sorting array through declarative: " + decSort(list1));
        ImpQuickSort.quickSort(list2, 0, list2.size() - 1);
        System.out.println("Sorting array through imperative: " + list2);

    }

    static class ImpQuickSort {
        static Integer partition(List<Integer> array, Integer low, Integer high) {
            Integer pivot = array.get(high);
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (array.get(j) <= pivot) {
                    i++;
                    int temp = array.get(i);
                    array.set(i, array.get(j));
                    array.set(j, temp);
                }
            }
            int temp = array.get(i + 1);
            array.set(i + 1, array.get(high));
            array.set(high, temp);
            return (i + 1);
        }

        static void quickSort(List<Integer> array, int low, int high) {
            if (low < high) {
                int pi = partition(array, low, high);
                quickSort(array, low, pi - 1);
                quickSort(array, pi + 1, high);
            }
        }
    }

    public static List<Integer> decSort(List<Integer> list) {
        Collections.sort(list);
        return list;
    }


}
