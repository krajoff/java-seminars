package org.example;

import java.io.*;


public class Tour1 {
    public static void main(String[] args) throws Exception {
        String relativePath = "input1.txt";
        String[] inputs = read(relativePath);
        String[] output = emoBoy(inputs);
        // String[] output = checkPrint(inputs);
        // String input = Arrays.toString(inputs);
        // System.out.printf(input);
        writeArray(output, "output.txt");
        // writeNumber(100, "output.txt");
        // NodeTasks nodeTasks = new NodeTasks();
        // nodeTasks.res();
    }

    public static String[] checkPrint(String[] inputs) {
        String[] output = new String[1];
        StringBuilder word = new StringBuilder();
        String keyboard = inputs[1];
        int position = 0;
        StringBuilder action = new StringBuilder();
        char ch;

        for (int i = 0; i < keyboard.length(); i++) {
            ch = keyboard.charAt(i);
            if (ch == '<') {
                action = new StringBuilder();
                while (ch != '>') {
                    action.append(ch);
                    i++;
                    ch = keyboard.charAt(i);
                }
                if (ch == '>')
                    action.append(ch);
                // System.out.println(action);
                switch (action.toString()) {
                    case ("<delete>"):
                        word.delete(position, position + 1);
                        //System.out.println(word);
                        break;
                    case ("<bspace>"):
                        if (position > 0) {
                            word.delete(position - 1, position);
                            position--;
                        }
                        break;
                    case ("<left>"):
                        if (position > 0)
                            position--;
                        break;
                    case ("<right>"):
                        if (position < word.length())
                            position++;
                        break;
                }
            } else {
                word.insert(position, ch);
                position++;
            }
        }
        if (word.toString().equals(inputs[0])) {
            output[0] = "Yes";
        } else {
            output[0] = "No";
        }
        return output;
    }

    static String[] read(String file) throws IOException {
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
        String[] array = string.split("\n");
        return array;
    }

    static void writeArray(String[] txt, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        int n = txt.length;
        for (int i = 0; i < n; i++) {
            fw.write(txt[i]);
        }
        fw.close();
    }

    static void writeNumber(long number, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        String output = Long.toString(number);
        fw.write(output);
        fw.close();
    }


    private static <Matcher> String[] emoBoy(String[] inputs) {
        int numbers = inputs.length;
        String[] output = new String[numbers];
        for (int i = 0; i < numbers; i++) {
            if (inputs[i].length() < 8) {
                output[i] = "NO";
            } else if (!inputs[i].matches(".*\\d+.*")) {
                output[i] = "NO";
            } else if (!inputs[i].matches(".*[A-Z]+.*")) {
                output[i] = "NO";
            } else if (!inputs[i].matches(".*[a-z]+.*")) {
                output[i] = "NO";
            } else {
                output[i] = "YES";
            }
        }
        return output;
    }


}