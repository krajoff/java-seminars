package junior;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Integer a = 10000;
        Integer b = 10000;
        Integer c = 100;
        Integer d = 100;
        int e = 10000;
        int f = 100;
        
        returnSum(1,1);
        System.out.printf("%b %b %b %b %b %b", a == e, b == e, a == b, c == f, d == f, c == d);
    }
    public static int[] returnSum(int a, int b){
        return new int[]{1,2};
    }

}