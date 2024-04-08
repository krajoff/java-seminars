import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution13 {
    public int romanToInt(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int num1 = simple(s.charAt(i));
            if (i + 1 < s.length()) {
                int num2 = simple(s.charAt(i + 1));
                if (num1 >= num2) {
                    sum = sum + num1;
                } else {
                    sum = sum - num1;
                }
            } else {
                sum = sum + num1;
            }
        }
        return sum;
    }

    public int simple(char letter) {
        return switch (letter) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }

}


public class RomantoInteger_Task13 {
    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();
        if (solution13.romanToInt("III") == 3) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution13.romanToInt("III"));
        }
        if (solution13.romanToInt("LVIII") == 58) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution13.romanToInt("LVIII"));
        }
        if (solution13.romanToInt("MCMXCIV") == 1994) {
            System.out.println("3.Good");
        } else {
            System.out.print("3.Bad: ");
            System.out.println(solution13.romanToInt("MCMXCIV"));
        }
    }


}
