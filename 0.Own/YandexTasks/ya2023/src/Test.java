import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws Exception {
//        String[] input = read("input.txt");
//        writeNumber(ex1(input), "output.txt");
        String[] numbers = standardRead();
        standardWrite(ex1(numbers));
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

    static void writeNumber(long number, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        String output = Long.toString(number);
        fw.write(output);
        fw.close();
    }

    static String[] standardRead() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = reader.readLine().split(" ");
        reader.close();
        return numbers;
    }

    static void standardWrite(long number) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        writer.write(String.valueOf(number));
        writer.close();
    }

    static long ex1(String[] input) {
        long A = Long.parseLong(input[0]);
        long B = Long.parseLong(input[1]);
        return A+B;
    }
}
