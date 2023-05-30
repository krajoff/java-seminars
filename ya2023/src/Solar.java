import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Solar {
    public static void main(String[] args) throws Exception {
        String[] input = read("input.txt");
//        System.out.println("input=" + Arrays.toString(input));
        writeNumber(ex1(input), "output.txt");
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

    static void writeNumber(int number, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        String output = Long.toString(number);
        fw.write(output);
        fw.close();
    }

    static int ex1(String[] input) {
        String[] numbers = input[0].split(" ");
        int numPlots = Integer.parseInt(numbers[0]);
        int numPeople = Integer.parseInt(numbers[1]);
        String[] strPeople = input[2].split(" ");
        String[] strPlot = input[1].split(" ");
        List<Long> listPeople = new ArrayList<>();
        long[] listPlot = new long[numPlots];
        for (int i = 0; i < numPeople; i++) {
            listPeople.add(Long.parseLong(strPeople[i]));
        }
        for (int i = 0; i < numPlots; i++) {
            listPlot[i] = Long.parseLong(strPlot[i]);
        }
        int count = 0;
        for (int i = 0; i < numPlots - 1; i++) {
            long delta;
            if (i < numPlots - 2) {
                long[] temp = Arrays.copyOfRange(listPlot, i + 1, numPlots - 1);
                delta = listPlot[i] - Arrays.stream(temp).max().getAsLong();
            } else {
                delta = listPlot[i] - listPlot[numPlots - 1];
            }
            Collections.sort(listPeople);
            if (delta > 0) {
                for (int k = listPeople.size() - 1; k > -1; k--) {
                    if (listPeople.get(k) <= delta) {
                        count++;
                        listPeople.remove(k);
                        break;
                    }
                }
            }
        }
        Collections.sort(listPeople);
        for (int k = listPeople.size() - 1; k > -1; k--) {
            if (listPeople.get(k) <= listPlot[numPlots - 1]) {
                count++;
                break;
            }
        }
        return count;
    }
}

