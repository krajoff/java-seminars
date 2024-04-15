package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class FileDealing_3 {
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

class Solution_task3 {
    int numberNodes, numberLines;
    int[][] descriptionLines;
    int numberOffers;
    int[][] offers;
    int numberRequests;
    int[][] requests;

    void coverData(String[] input) {
        int l = 0;
        String[] line = input[l].split(" ");
        numberNodes = Integer.parseInt(line[0]);
        numberLines = Integer.parseInt(line[1]);
        descriptionLines = new int[numberLines][3];
        l++;
        for (int i = l; i < numberLines + l; i++) {
            line = input[i].split(" ");
            descriptionLines[i - l] = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
        }
        l = l + numberLines;
        numberOffers = Integer.parseInt(input[l]);
        l++;
        offers = new int[numberOffers][4];
        for (int i = l; i < numberOffers + l; i++) {
            line = input[i].split(" ");
            offers[i - l] = Stream.of(line)
                    .mapToInt(Integer::parseInt).toArray();
        }
        l = l + numberOffers;
        numberRequests = Integer.parseInt(input[l]);
        requests = new int[numberRequests][3];

        System.out.println("numberNodes = " + numberNodes +
                " numberLines = " + numberLines);
        System.out.println("descriptionLines = "
                + Arrays.toString(descriptionLines));
        System.out.println("numberOffers = " + numberOffers);
        System.out.println("offers = " + Arrays.toString(offers));
        System.out.println("numberRequests = " + numberRequests);
        System.out.println("requests = " + Arrays.toString(requests));
    }
}

public class Tour2_task3 {
    public static void main(String[] args) throws IOException {
        String relativePath = "input_task3_2.txt";
        FileDealing_3 fileDealing = new FileDealing_3();
        String[] inputs = fileDealing.read(relativePath);
        Solution_task3 solution = new Solution_task3();
        solution.coverData(inputs);
    }
}
