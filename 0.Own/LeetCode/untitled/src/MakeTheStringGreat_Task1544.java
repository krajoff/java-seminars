import java.util.Locale;

class Solution1544 {
    public String makeGood(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int counter;
        do {
            counter = 0;
            for (int i = 0; i < stringBuilder.length() - 1; i++) {
                if ((stringBuilder.charAt(i) + 32)
                        == stringBuilder.charAt(i + 1) ||
                        (stringBuilder.charAt(i) - 32)
                                == stringBuilder.charAt(i + 1)) {
                    stringBuilder.delete(i, i + 2);
                    counter++;
                }
            }
        } while (counter != 0);
        return stringBuilder.toString();
    }
}

public class MakeTheStringGreat_Task1544 {
    public static void main(String[] args) {
        Solution1544 solution1544 = new Solution1544();
        if (solution1544.makeGood("leEeetcode").equals("leetcode")) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution1544.makeGood("leEeetcode"));
        }
        if (solution1544.makeGood("abBAcC").isEmpty()) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution1544.makeGood("abBAcC"));
        }
        if (solution1544.makeGood("s").equals("s")) {
            System.out.println("3.Good");
        } else System.out.print("3.Bad");
    }
}
