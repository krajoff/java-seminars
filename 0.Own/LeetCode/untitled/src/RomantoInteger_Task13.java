import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution13 {
    public int romanToInt(String s) {
        char letter;
        List<Integer> numbers = new ArrayList<>();


        for (int i = 0; i < s.length(); i++) {

            twoletters = s.substring(i,i+2)



            letter = s.charAt(i);
            switch (letter) {
                case 'I':
                    numbers.add(1);
                    break;
                case 'V':
                    numbers.add(5);
                    break;
                case 'X':
                    numbers.add(10);
                    break;
                case 'L':
                    numbers.add(50);
                    break;
                case 'C':
                    numbers.add(100);
                    break;
                case 'D':
                    numbers.add(500);
                    break;
                case 'M':
                    numbers.add(1000);
                    break;
            }
        }
        int sum, temp;
        sum = temp = 0;
        for (int i = 0; i < numbers.size(); i++) {
            if (i > 1 && numbers.get(i) > numbers.get(i - 1)) {
                sum = -temp;
                temp = 0;
            }
            temp = temp + numbers.get(i);
        }
        sum=temp;
        return sum;
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
