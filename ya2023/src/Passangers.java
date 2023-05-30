import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Passangers {
    public static void main(String[] args) throws Exception {
        String[] input = read("passangers.txt");
        writeStringBuilder(ex(input), "passangers_out.txt");
    }

    static String[] read(String file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(fr);
        StringBuilder input = new StringBuilder();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            input.append(line).append("\n");
        }
        String string = input.toString();
        String[] array = string.split("\n");
        fr.close();
        return array;
    }

    static void writeStringBuilder(StringBuilder stringBuilder, String file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(stringBuilder);
        }
    }

    static StringBuilder ex(String[] input) throws IOException {
        StringBuilder anq = new StringBuilder();
        int rows = Integer.parseInt(input[0]);
        String[] seats = new String[rows];
        System.arraycopy(input, 1, seats, 0, rows);

        int nReq = Integer.parseInt(input[rows + 1]);
        String[] requars = new String[nReq];
        System.arraycopy(input, rows + 2, requars, 0, nReq);

        for (int i = 0; i < nReq; i++) {
            String[] rbp = requars[i].split(" ");
            for (int j = 0; j < rows; j++) {
                String reqRow = "";
                String[] row = seats[j].split("_");
                if (rbp[1] == "left") reqRow = row[0];
                else reqRow = row[1];
                String K = rbp[1] + " " + rbp[2];
                if (K.equals("left window")) {
                    if (K.equals("left window")) reqRow = row[0];
                    else reqRow = row[1];
                    if (!reqRow.substring(0, Integer.parseInt(rbp[0]) - 1).contains("#")) {
                        anq.append(Arrays.toString(seats));
                        anq.append("Passengers can take seats: ");
                        anq.append(seats(K, Integer.parseInt(rbp[0]), j + 1));
                        if (j != rows - 1) anq.append("\n");
                        break;
                    } else if (j == rows - 1) anq.append("Cannot fulfill passengers requirements");
                } else {
                    if (K.equals("right window")) reqRow = row[1];
                    else reqRow = row[0];
                }
            }
        }
        return anq;
    }

    static String seats(String side, int numbers, int row) {
        String letters = "ABCDEF";
        String result = "";
        String[] rl = side.split(" ");
        int start = 0;
        int end = numbers;
        if (rl[0].equals("left")) {
            letters = letters.substring(0, 2);
        } else {
            letters = letters.substring(3, 5);
        }
        if (side.equals("right aisle")) {
            start = 3 - numbers;
            end = 3;
        }
        for (int i = start; i < end; i++) {
            result = String.join(" ", result,
                    row + letters.substring(i, i + 1));
        }
        return result;
    }
}