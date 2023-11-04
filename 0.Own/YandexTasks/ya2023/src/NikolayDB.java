import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class NikolayDB {
    public static void main(String[] args) throws Exception {
        String[] input = read("input.txt");

//        System.out.println(Arrays.toString(input));
        List<Integer> output = ex1(input);
        String result = output.toString();
        String numberString = output.stream().map(String::valueOf)
                .collect(Collectors.joining("\n"));
//        System.out.println(numberString);
        writeArray(numberString, "output.txt");
    }

    static List<Integer> ex1(String[] input) {
        String[] numbers = input[0].split(" ");
        int db = Integer.parseInt(numbers[0]);
        int srv = Integer.parseInt(numbers[1]);
        int evn = Integer.parseInt(numbers[2]);
        int[][] dbs = new int[db][srv];
        int[] rst = new int[db];
        List<Integer> pull = new ArrayList<>(db);

        int index = 0;
        int evl;
        Integer max;
        Integer min;
        int dbdis;
        int srvdis;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < db; i++) {
            pull.add(0);
            for (int j = 0; j < srv; j++) dbs[i][j] = 1;
        }
        for (int i = 1; i < evn + 1; i++) {
            String[] str = input[i].split(" ");
            if (str[0].equals("DISABLE")) {
                dbdis = Integer.parseInt(str[1]) - 1;
                srvdis = Integer.parseInt(str[2]) - 1;
                dbs[dbdis][srvdis] = 0;
            }
            if (str[0].equals("RESET")) {
                int dbrst = Integer.parseInt(str[1]);
                ++rst[dbrst - 1];
                for (int j = 0; j < srv; j++) dbs[dbrst - 1][j] = 1;
            }
            if (str[0].equals("GETMAX") || str[0].equals("GETMIN")) {
                for (int k = 0; k < db; k++) {
                    evl = 0;
                    for (int j = 0; j < srv; j++) {
                        evl += dbs[k][j];
                    }
                    pull.set(k, rst[k] * evl);
                }
                if (str[0].equals("GETMAX")) {
                    max = Collections.max(pull);
                    index = pull.indexOf(max);
                }
                if (str[0].equals("GETMIN")) {
                    min = Collections.min(pull);
                    index = pull.indexOf(min);
                }
                ans.add(index + 1);
            }
        }
        return ans;
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

    static void writeArray(String txt, String file) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(txt);
        fw.close();
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


}
