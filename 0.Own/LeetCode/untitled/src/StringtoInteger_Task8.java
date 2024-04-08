import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution8 {
    public int myAtoi(String s) {
        int total = 0;
        Pattern pattern = Pattern.compile("[. +a-zA-Z]");
        Matcher matcher = pattern.matcher(s);
        String string = matcher.replaceAll("");
        //System.out.println(string);
        total=Integer.parseInt(string);
        return total;
    }
}

public class StringtoInteger_Task8 {
    public static void main(String[] args) {
        Solution8 solution8 = new Solution8();
        if (solution8.myAtoi("42") == 42) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution8.myAtoi("42"));
        }
        if (solution8.myAtoi("   +-42") == -42) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution8.myAtoi("   -42"));
        }
        if (solution8.myAtoi("4193 with words") == 4193) {
            System.out.println("3.Good");
        } else {
            System.out.print("3.Bad: ");
            System.out.println(solution8.myAtoi("4193 with words"));
        }
        if (solution8.myAtoi("words and 987") == 0) {
            System.out.println("4.Good");
        } else {
            System.out.print("4.Bad: ");
            System.out.println(solution8.myAtoi("words and 987"));
        }

    }
}
