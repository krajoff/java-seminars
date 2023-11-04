import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String nnm = "10 2 5";
        String g = "50 47 40 51 42";

//        String nnm = "13 0 5";
//        String g = "42 47 40 49 42";

//        String nnm = "0 1 1";
//        String g = "49";

//        String nnm = "2 1 4";
//        String g = "10 52 42 11";


        String[] firstdata = nnm.split(" ");
        long n10 = Integer.parseInt(firstdata[0]);
        long n50 = Integer.parseInt(firstdata[1]);
        int m = Integer.parseInt(firstdata[2]);

        String[] G = g.split(" ");
        List<Long> list = new ArrayList<>();
        long sum = 0;
        long el;
        for (String s : G) {
            el = Integer.parseInt(s);
            list.add(el);
            sum += el;
        }
        if (n10 * 10 + n50 * 50 > sum) {
            System.out.println("-1");
        } else {
            Collections.sort(list);
            int size = list.size();
            long temp;
            long n = 0;
            double value, fractionalPart, integralPart;
            for (int i = size - 1; i > -1; i--) {
                if (n50 > 0) {
                    value = (double) list.get(i) / 50;
                    fractionalPart = value % 1;
                    integralPart = value - fractionalPart;
                    if (integralPart > 0) {
                        temp = list.get(i) - (long) integralPart * 50;
                        list.set(i, temp);
                        n50 -= (long) integralPart;
                        n++;
                    }
                }
                if (n10 > 0) {
                    value = (double) list.get(i) / 10;
                    fractionalPart = value % 1;
                    integralPart = value - fractionalPart;
                    if (integralPart > 0) {
                        temp = list.get(i) - (long) integralPart * 10;
                        list.set(i, temp);
                        n10 -= (long) integralPart;
                        n++;
                    }
                }
            }
            if (n10 > 0 || n50 > 0) {
                System.out.println("-1");
            } else {
                System.out.println(n);
            }
        }
    }
}

