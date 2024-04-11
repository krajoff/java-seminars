package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class FileDealing {
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
    int[][] field;

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
        field = new int[max_y + 1][max_x + 1];
        int row, col, player, result;
        for (int i = 0; i < number_turns; i++) {
            row = turns[i][0];
            col = turns[i][1];
            if (i % 2 == 0) {
                player = 1;
                field[row][col] = player;
            } else {
                player = 2;
                field[row][col] = player;
            }
            printFiled(field);
            result = check(player, col, row);
            if (result == 1) {
                System.out.println(1);
            } else if (result == 2) {
                System.out.println(2);
            }

        }
        return field;
    }

    int check(int player, int col, int row) {

        // along horizon
        int flag = 0;
        for (int x = col; x < max_x; x++) {
            if (field[row][x] == player) flag++;
            else break;
        }
        for (int x = col; x > -1; x--) {
            if (field[row][x] == player) flag++;
            else break;
        }
        if (flag == 5) return player;

        // along vertical
        flag = 0;
        for (int y = row; y < max_y; y++) {
            if (field[y][col] == player) flag++;
            else break;
        }
        for (int y = row; y > -1; y--) {
            if (field[y][col] == player) flag++;
            else break;
        }
        if (flag == 5) return player;

        // along diagonal up-right
        flag = 0; int x = col; int y = row;
        while (x < max_x && y > -1 && field[y][x] == player) {
            flag++; x++; y--;
        }
        x = col - 1; y = row + 1;
        while (x > -1 && y < max_y && field[y][x] == player) {
            flag++; x--; y++;
        }
        if (flag == 5) return player;

        // along diagonal up-left
        flag = 0; x = col; y = row;
        while (x > -1 && y > -1 && field[y][x] == player) {
            flag++; x--; y--;
        }
        x = col + 1; y = row + 1;
        while (x < max_x && y < max_y && field[y][x] == player) {
            flag++; x++; y++;
        }
        if (flag == 5) return player;

        System.out.println("END CHECKING");
        return -1;
    }

    void printFiled(int[][] field) {
        for (int[] row : field) {
            for (int el : row)
                System.out.print(el + " | ");
            System.out.println();
        }
        System.out.println();
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
