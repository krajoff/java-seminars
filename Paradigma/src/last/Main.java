package last;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Double[] predict = new Double[]{0.0, 0.1, 1.1, 1.0, 2.0};
        Double[] real = new Double[]{0.0, 0.1, 1.1, 1.0, 2.1};
        int p;
        System.out.printf("1.Task MSE = %.3f", mse(predict, real));
        System.out.println();
        Integer[] unsorted = new Integer[]{2, 0, -4, -5, 10, 9, -1,
                6, 8, -3, 5, 7, -2, 1, 4, 3};
        System.out.println("2.Task. unsorted array: " + Arrays.toString(unsorted));
        mergeSort(unsorted, unsorted.length);
        System.out.println("sorted array: " + Arrays.toString(unsorted));
        System.out.println("3.Binary search: " +
                Arrays.binarySearch(unsorted, 6));
    }

    public static Double mse(Double[] real, Double[] predict) {
        Double sum = 0.0;
        for (int i = 0; i < real.length; i++) {
            sum += Math.pow((real[i] - predict[i]), 2);
        }
        return sum / real.length;
    }

    public static void mergeSort(Integer[] unsorted, int n) {
        if (n < 2) {
            return;
        }
        int mid = n / 2;
        Integer[] l = new Integer[mid];
        Integer[] r = new Integer[n - mid];
        for (int i = 0; i < mid; i++) l[i] = unsorted[i];
        for (int i = mid; i < n; i++) r[i - mid] = unsorted[i];
        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(unsorted, l, r, mid, n - mid);
    }

    public static void merge(Integer[] a, Integer[] l, Integer[] r,
                             int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        while (i < left)
            a[k++] = l[i++];
        while (j < right)
            a[k++] = r[j++];
    }
}
