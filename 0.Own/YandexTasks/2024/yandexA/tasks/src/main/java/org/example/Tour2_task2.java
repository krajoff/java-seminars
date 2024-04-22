package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
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

    void writeTwoValues(long totalPrice, int[] buyByDays) throws IOException {
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
    long totalPrice;
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
//        System.out.println("eatDays = " + eatDays +
//                " storageDays = " + storageDays);
//        System.out.println("priceByDays = " + Arrays.toString(priceByDays));
    }

    void solve(String[] input) throws IOException {
        coverData(input);
        buyByDays = new int[eatDays];
        int minPrice = Integer.MAX_VALUE;
        Stack<Integer> minPriceStack = new Stack<>();
        int j, k, fishs;
        totalPrice = fishs = 0;
        for (int i = 0; i < eatDays; i++) {

            // Return minimum price stack
            j = 0;
            while (j < storageDays && i - j > -1) {
                if (priceByDays[i - j] < minPrice) {
                    minPrice = priceByDays[i - j];
                }
                j++;
            }
            minPriceStack.push(minPrice);
            minPrice = Integer.MAX_VALUE;

            // Return fishes
            if (i > 0) {
                if (minPriceStack.get(i).equals(minPriceStack.get(i - 1))) {
                    fishs++;
                } else {
                    totalPrice += (long) fishs * minPriceStack.get(i - 1);
                    buyByDays[i] = fishs;
                    fishs = 0;
                }

//                k = i;
//                while (k < eatDays && minPriceStack.get(i)
//                        .equals(minPriceStack.get(k))) {
//                    fishs++;
//                    j++;
//                }
//                totalPrice += (long) fishs * minPriceStack.get(i);
//                buyByDays[i] = fishs;
//                i += (j - i - 1);
//                fishs = 0;
            }


        }


        for (int i = 0; i < eatDays; i++) {

        }

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
        String relativePath = "input_task2_2.txt";
        //String relativePath = "input.txt";
        FileDealing_2 fileDealing = new FileDealing_2();
        String[] inputs = fileDealing.read(relativePath);
        Solution_task2 solution = new Solution_task2();
        solution.solve(inputs);

    }
}
