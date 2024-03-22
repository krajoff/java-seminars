package org.example;

import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    public static void main(String[] args) throws Exception {
        String relativePath = "input.txt";
        String[] inputs = read(relativePath);
        String input = Arrays.toString(inputs);
        System.out.printf(input);
        writeNumber(100, "output.txt");
    }

    static String[] read(String file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(fr);
        StringBuilder input = new StringBuilder();
        String line;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            input.append(line).append("\n");
        }
        String string = input.toString();
        String[] array = string.split("\n");
        fr.close();
        return array;
    }

    static void writeArray(String[] txt, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        int n = txt.length;
        for (int i = 0; i < n; i++) {
            fw.write(i + " : " + txt[i] + "\n");
        }
        fw.close();
    }

    static void writeNumber(long number, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        String output = Long.toString(number);
        fw.write(output);
        fw.close();
    }


}

