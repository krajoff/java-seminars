package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FileDealing_2 {
    String[] read(String file) throws IOException {
        StringBuilder input = new StringBuilder();
        try (var fr = new FileReader(file);
             var br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line).append("\n");
            }
        }
        String string = input.toString();
        return string.split("\n");
    }

    void writeTwoValues(int totalPrice, int[] buyByDays) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(String.valueOf(totalPrice));
        fw.write("\n");
        String string = Arrays
                .stream(buyByDays)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        fw.write(string);
        fw.close();
    }

}

class Solution_task2 {
    int eatDays, storageDays;
    int[] priceByDays;
    int totalPrice;
    int[] buyByDays;

    void coverData(String[] input) {
        String[] firstLine = input[0].split(" ");
        eatDays = Integer.parseInt(firstLine[0]);
        storageDays = Integer.parseInt(firstLine[1]);
        priceByDays = new int[eatDays];
        priceByDays = Stream.of(input[1]
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        System.out.println("eatDays = " + eatDays +
                " storageDays = " + storageDays);
        System.out.println("priceByDays = " + Arrays.toString(priceByDays));
    }

    void solve(String[] input) throws IOException {
        coverData(input);
        buyByDays = new int[eatDays];
        List<Integer> temp = new ArrayList<>();
        List<Integer> minPriceByDay = new ArrayList<>();

        int i;
        int j = 0;
        while (j < eatDays) {
            i = 0;
            while (i < storageDays && j - i > -1) {
                temp.add(priceByDays[j - i]);
                i++;
            }
            minPriceByDay.add(Collections.min(temp));
            temp.clear();
            j++;
        }

        int fishs = 0;
        totalPrice = 0;
        for (i = 0; i < eatDays; i++) {
            j = i;
            while (j < eatDays && minPriceByDay.get(i)
                    .equals(minPriceByDay.get(j))) {
                fishs++;
                j++;
            }
            totalPrice += fishs * minPriceByDay.get(i);
            buyByDays[i] = fishs;
            i += (j - i - 1);
            fishs = 0;
        }

        System.out.println("totalPrice = " + totalPrice);
        System.out.println("minPriceByDay = " + minPriceByDay);
        System.out.println("buyByDays = " + Arrays.toString(buyByDays));

        FileDealing_2 fileDealing2 = new FileDealing_2();
        fileDealing2.writeTwoValues(totalPrice, buyByDays);
    }

    void printFiled(int[][] input) {
        for (int[] row : input) {
            for (int el : row)
                System.out.print(el + " | ");
            System.out.println();
        }
        System.out.println();
    }

}

public class Tour2_task2 {
    public static void main(String[] args) throws IOException {
        String relativePath = "input_task2_1.txt";
        FileDealing_2 fileDealing = new FileDealing_2();
        String[] inputs = fileDealing.read(relativePath);
        Solution_task2 solution = new Solution_task2();
        solution.solve(inputs);

    }
}
