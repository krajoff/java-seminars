package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

class FileDealing {
    String[] read(String file) throws IOException {
        FileReader fr = new FileReader(file);
        StringBuilder input = new StringBuilder();
        BufferedReader br = new BufferedReader(fr);
        try {
            String line;
            while ((line = br.readLine()) != null) {
                input.append(line).append("\n");
            }
        } finally {
            br.close();
            fr.close();
        }
        String string = input.toString();
        return string.split("\n");
    }

    void writeArray(String[] txt) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        for (String s : txt)
            fw.write(s);
        fw.close();
    }
}

class Solution {
    int max_x;
    int max_y;

    int[][] convert(String[] input) {
        max_x = max_y = Integer.MIN_VALUE;
        int size = input.length - 1;
        int[][] result = new int[size][2];
        String[] row;
        int min_x, min_y, x, y;
        min_x = min_y = 0;
        for (int i = 0; i < size; i++) {
            row = input[i + 1].split(" ");
            x = Integer.parseInt(row[0]);
            y = Integer.parseInt(row[1]);
            result[i][0] = x;
            result[i][1] = y;
            if (x < min_x) min_x = x;
            if (y < min_y) min_y = y;
            if (x > max_x) max_x = x;
            if (y > max_y) max_y = y;
        }
        if (min_x != 0 || min_y != 0) {
            for (int i = 0; i < size; i++) {
                result[i][0] = result[i][0] - min_x;
                result[i][1] = result[i][1] - min_y;
            }
        }
        return result;
    }

    int[][] fillField(String[] input) {
        int[][] turns = convert(input);
        int number_turns = turns.length;
        int[][] field = new int[max_x + 1][max_y + 1];
        int x, y;
        for (int i = 0; i < number_turns; i++) {
            if (i % 2 == 0)
                field[turns[i][0]][turns[i][1]] = 1;
            else
                field[turns[i][0]][turns[i][1]] = -1;
        }
        return field;
    }

    String check(int[][] field, int player) {
        for
    }

    void printFiled(int[][] field) {
        for (int[] row : field) {
            for (int el : row)
                System.out.print(el + " | ");
            System.out.println();
        }
    }
}

public class Tour2 {
    public static void main(String[] args) throws IOException {
        String relativePath = "input_task1_1.txt";
        FileDealing fileDealing = new FileDealing();
        String[] inputs = fileDealing.read(relativePath);
        Solution solution = new Solution();
        int[][] field = solution.fillField(inputs);
        solution.printFiled(field);

        String[] output = {"s", "d"};
        fileDealing.writeArray(output);

    }
}
