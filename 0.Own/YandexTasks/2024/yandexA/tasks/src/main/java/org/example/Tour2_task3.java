package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

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

class Solution_task3 {}
public class Tour2_task3 {
    public static void main(String[] args) throws IOException {
        String relativePath = "input_task3_1.txt";
        FileDealing_3 fileDealing = new FileDealing_3();
        String[] inputs = fileDealing.read(relativePath);
        Solution_task3 solution = new Solution_task3();
    }
}
