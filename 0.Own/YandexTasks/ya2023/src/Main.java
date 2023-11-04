import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] input = read("input.txt");
        long n = ex1(input);
        writeNumber(n, "output.txt");
    }

    static String[] read(String file) throws IOException {
        FileReader fr = new FileReader(file);
        Scanner scan = new Scanner(fr);
        StringBuilder input = new StringBuilder();
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            input.append(line + "\n");
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

    static long ex1(String[] input) {
        String[] numbers = input[1].split(" ");
        ArrayList<Long> list = new ArrayList<>();
        if (numbers.length == 1) return -1;
        for (int i = 0; i < numbers.length; i++) {
            list.add(Long.parseLong(numbers[i]));
            boolean b = i > 0 && list.get(i) < list.get(i - 1);
            if (b) return -1;
        }
        return list.get(numbers.length - 1) - list.get(0);
    }
}

