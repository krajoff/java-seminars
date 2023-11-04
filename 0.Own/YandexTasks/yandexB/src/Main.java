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
//        String w = "1000";
//        String nk = "5 5";
//        String str = "1404x1386\n" +
//                "1132x1110\n" +
//                "1061x1801\n" +
//                "1022x1519\n" +
//                "1529x1003";
//
        String w = "2000";
        String nk = "5 3";
        String str = "1000x1000\n" +
        "0x1500\n" +
        "640x930\n" +
        "640x1500\n" +
        "3000x1000\n";


//        String w = "4096";
//        String nk = "2 1";
//        String str = "640x4096\n" +
//        "4096x640";

        //System.out.println(str);

        String[] lines = str.split("\\n");
        System.out.println(Arrays.toString(lines));
        int W = Integer.parseInt(w);
        String[] nkparts = nk.split(" ");
        int n = Integer.parseInt(nkparts[0]);
        int k = Integer.parseInt(nkparts[1]);
        List<Integer> list = new ArrayList<>();
        int temp;
        for (int i = 0 ; i < n ; i++) {
            String[] wh = lines[i].split("x");
            System.out.println(wh[0] + " " + wh[1]);
            if (Double.parseDouble(wh[0]) != 0) {
                temp = (int) Math.ceil(Double.parseDouble(wh[1]) * W / Double.parseDouble(wh[0]));
            } else {
                temp = Integer.parseInt(wh[1]);
            }
            list.add(temp);
        }
        Collections.sort(list);
        int min = 0;
        int max = 0;
        for (int i = 0; i < k; i++) {
            min += list.get(i);
            max += list.get(list.size() - i - 1);
        }
        System.out.println("min:" + min + " max: " + max);
    }

}
