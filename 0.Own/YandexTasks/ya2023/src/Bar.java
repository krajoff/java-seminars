import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Bar {
    public static void main(String[] args) throws Exception {
        String[] input = read("input.txt");
        System.out.println(Arrays.toString(input));
        writeArray(ex1(input), "output.txt");
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
        for (int i = 0; i < n-1; i++) {
            fw.write(txt[i] + "\n");
        }
        fw.write(txt[n-1]);
        fw.close();
    }

    static void writeNumber(long number, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        String output = Long.toString(number);
        fw.write(output);
        fw.close();
    }

    static String[] ex1(String[] input) {
        String[] numbers = input[0].split(" ");
        int hightGlass = Integer.parseInt(numbers[0]);

        String[] glass = new String[hightGlass];
        System.arraycopy(input, 1, glass, 0, hightGlass);

        int numbersIng = Integer.parseInt(input[hightGlass + 1]);
        int count = hightGlass - 2;
        //glass[hightGlass - 1] = input[hightGlass];
        for (int i = 0; i < numbersIng; i++) {
            String[] stringIng = input[hightGlass + i + 2].split(" ");
            for (int j = 0; j < Integer.parseInt(stringIng[1]); j++) {
                char newChar = stringIng[2].charAt(0);
                glass[count] = input[count+1].replace(' ', newChar);
                count--;
            }
        }
        System.out.println("hight=" + hightGlass + " " + numbersIng);
        return glass;
    }

}
