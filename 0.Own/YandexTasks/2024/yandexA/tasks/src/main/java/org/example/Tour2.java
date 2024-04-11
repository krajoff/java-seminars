package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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

    void writeString(String s) throws IOException {
        FileWriter fw = new FileWriter("output.txt");
        fw.write(s);
        fw.close();
    }
}

class Solution {
    int max_x;
    int max_y;
    int[][] field;
    int turns;

    int[][] convert(String[] input) {
        int min_x, min_y, x, y;
        min_x = min_y = Integer.MAX_VALUE;
        max_x = max_y = Integer.MIN_VALUE;
        turns = input.length - 1;
        int[][] coordinates = new int[turns][2];
        String[] row;
        for (int i = 0; i < turns; i++) {
            row = input[i + 1].split(" ");
            x = Integer.parseInt(row[0]);
            y = Integer.parseInt(row[1]);
            coordinates[i][0] = x;
            coordinates[i][1] = y;
            if (x < min_x) min_x = x;
            if (y < min_y) min_y = y;
            if (x > max_x) max_x = x;
            if (y > max_y) max_y = y;
        }
        if (min_x > 0) {
            for (int i = 0; i < turns; i++)
                coordinates[i][0] = coordinates[i][0] - min_x;
            max_x = max_x - min_x;
        }
        if (min_y > 0) {
            for (int i = 0; i < turns; i++)
                coordinates[i][1] = coordinates[i][1] - min_y;
            max_y = max_y - min_y;
        }
        System.out.println(max_y);
        System.out.println(max_x);
        System.out.println(min_y);
        System.out.println(min_x);
        return coordinates;
    }

    int[][] fillField(String[] input) throws IOException {
        FileDealing fileDealing = new FileDealing();
        int[][] coordinates = convert(input);
        String string_result;
        field = new int[max_y + 1][max_x + 1];
        int row, col, player, result;
        for (int i = 0; i < turns; i++) {
            row = coordinates[i][1];
            col = coordinates[i][0];
            if (i % 2 == 0) player = 1;
            else player = 2;
            field[row][col] = player;
            printFiled(field);
            result = check(player, col, row);
            string_result = decisionMaker(result, i);
            if (string_result != null) {
                fileDealing.writeString(string_result);
                System.out.println(string_result);
                break;
            }
        }
        return field;
    }

    int check(int player, int col, int row) {
        int win = 5;

        // Along horizon
        int flag = 0;
        for (int x = col; x < max_x; x++) {
            if (field[row][x] == player) flag++;
            else break;
        }
        for (int x = col; x > -1; x--) {
            if (field[row][x] == player) flag++;
            else break;
        }
        if (flag == win) return player;

        // Along vertical
        flag = 0;
        for (int y = row; y < max_y; y++) {
            if (field[y][col] == player) flag++;
            else break;
        }
        for (int y = row; y > -1; y--) {
            if (field[y][col] == player) flag++;
            else break;
        }
        if (flag == win) return player;

        // Along diagonal up-right
        flag = 0;
        int x = col;
        int y = row;
        while (x < max_x && y > -1 && field[y][x] == player) {
            flag++;
            x++;
            y--;
        }
        x = col - 1;
        y = row + 1;
        while (x > -1 && y < max_y && field[y][x] == player) {
            flag++;
            x--;
            y++;
        }
        if (flag == win) return player;

        // Along diagonal up-left
        flag = 0;
        x = col;
        y = row;
        while (x > -1 && y > -1 && field[y][x] == player) {
            flag++;
            x--;
            y--;
        }
        x = col + 1;
        y = row + 1;
        while (x < max_x && y < max_y && field[y][x] == player) {
            flag++;
            x++;
            y++;
        }
        if (flag == win) return player;

        return -1;
    }

    String decisionMaker(int player, int turn) {
        String string = null;
        if (player == 1) {
            if (turn < turns - 1) {
                string = "Inattention";
            } else {
                string = "First";
            }
        } else if (player == 2) {
            if (turn < turns - 1) {
                string = "Inattention";
            } else {
                string = "Second";
            }
        }
        if (turn == turns - 1 && player == -1) {
            string = "Draw";
        }
        return string;
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
        String relativePath = "input_task1_4.txt";
        FileDealing fileDealing = new FileDealing();
        String[] inputs = fileDealing.read(relativePath);
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.convert(inputs)));

        int[][] field = solution.fillField(inputs);
//        solution.printFiled(field);

        //String[] output = {"s", "d"};
        //fileDealing.writeArray(output);

    }
}
