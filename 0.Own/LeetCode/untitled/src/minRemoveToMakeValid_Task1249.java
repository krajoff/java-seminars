import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

class Solution1249 {
    public String minRemoveToMakeValid(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int temp = 0;
        for (int i = 0; i < stringBuilder.length(); i++) {
            if (stringBuilder.charAt(i) == '(') {
                temp++;
            } else if (stringBuilder.charAt(i) == ')') {
                temp--;
                if (temp < 0) {
                    stringBuilder.delete(i, i + 1);
                    temp = 0;
                    i--;
                }
            }
        }
        //System.out.println(stringBuilder);
        Stack<Integer> num = new Stack<>();
        StringBuilder newStringBuilder;
        int position;
        if (temp > 0) {
            newStringBuilder = new StringBuilder();
            for (int i = 0; i < stringBuilder.length(); i++) {
                if (stringBuilder.charAt(i) == '(') {
                    newStringBuilder.append((char) 93);
                    num.push(i);
                } else if (stringBuilder.charAt(i) == ')') {
                    position = num.pop();
                    newStringBuilder
                            .replace(position,
                                    position+1, "(");
                    newStringBuilder.append(')');

                } else {
                    newStringBuilder
                            .append(stringBuilder
                                    .charAt(i));
                }
            }
        } else {
            newStringBuilder = stringBuilder;
        }
        for (int i = 0; i < newStringBuilder.length(); i++) {
            if (newStringBuilder.charAt(i) == (char) 93) {
                newStringBuilder.delete(i, i + 1);
                i--;
            }

        }

        return newStringBuilder.toString();
    }
}


public class minRemoveToMakeValid_Task1249 {
    public static void main(String[] args) {
        Solution1249 solution1249 = new Solution1249();
        if (solution1249.minRemoveToMakeValid("lee(t(c)o)de)")
                .equals("lee(t(c)o)de")) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("lee(t(c)o)de)"));
        }
        if (solution1249.minRemoveToMakeValid("a)b(c)d")
                .equals("ab(c)d")) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("a)b(c)d"));
        }
        if (solution1249.minRemoveToMakeValid("))((").isEmpty()) {
            System.out.println("3.Good");
        } else {
            System.out.print("3.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("))(("));
        }
        if (solution1249.minRemoveToMakeValid("(())")
                .equals("(())")) {
            System.out.println("4.Good");
        } else {
            System.out.print("4.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("(())"));
        }
        if (solution1249.minRemoveToMakeValid("(((())")
                .equals("(())")) {
            System.out.println("5.Good");
        } else {
            System.out.print("5.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("(((())"));
        }
        if (solution1249.minRemoveToMakeValid("a((()((a")
                .equals("a()a")) {
            System.out.println("6.Good");
        } else {
            System.out.print("6.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("a((()((a"));
        }
        if (solution1249.minRemoveToMakeValid("())()(((")
                .equals("()()")) {
            System.out.println("7.Good");
        } else {
            System.out.print("7.Bad: ");
            System.out.println(solution1249
                    .minRemoveToMakeValid("())()((("));
        }
    }
}
