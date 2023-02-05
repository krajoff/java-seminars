import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        ex0();
    }

    static void ex0(){
        ArrayList<Integer> list1 = new ArrayList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i<100_000; i++) {
            list1.add(0,5);
        }
        long finishTime1 =System.currentTimeMillis();
        System.out.println("ArrayList time is " + (finishTime1-startTime1));

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i<100_000; i++) {
            list2.add(0, 5);
        }
        long finishTime2 =System.currentTimeMillis();
        System.out.println("LinkedList time is " + (finishTime2-startTime2));
    }
}
