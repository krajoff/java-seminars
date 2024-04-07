import java.util.Stack;

class Solution678 {
    public boolean checkValidString(String s) {
        StringBuilder sb = new StringBuilder(s);
        int count = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ')' && i > 0) {
                count++;
                for (int j = i - 1; j > -1; j--) {
                    if (sb.charAt(j) == '(') {
                        sb.replace(j, j + 1, " ");
                        sb.replace(i, i + 1, " ");
                        break;
                    }
                }
            } else if (sb.charAt(i) == '(') {
                count--;
            }
        }
//        System.out.println(sb);


        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                stack.push(i);
            }
            if (sb.charAt(i) == '*' && !stack.isEmpty()) {
                sb.replace(i, i + 1, " ");
                int position = stack.pop();
                sb.replace(position, position + 1, " ");
            }
        }
        stack.clear();

        for (int i = sb.length() - 1; i > -1; i--) {
            if (sb.charAt(i) == ')') {
                stack.push(i);
            }
            if (sb.charAt(i) == '*' && !stack.isEmpty()) {
                sb.replace(i, i + 1, " ");
                int position = stack.pop();
                sb.replace(position, position + 1, " ");

            }
        }
        // Remove ' '
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ' || sb.charAt(i) == '*') {
                sb.delete(i, i + 1);
                i--;
            }

        }
        return sb.isEmpty();
    }
}

public class ValidParenthesisString_Task678 {
    public static void main(String[] args) {
        Solution678 solution678 = new Solution678();
        if (solution678.checkValidString("()")) {
            System.out.println("1.Good");
        } else {
            System.out.print("1.Bad: ");
            System.out.println(solution678
                    .checkValidString("()"));
        }
        if (solution678.checkValidString("(*)")) {
            System.out.println("2.Good");
        } else {
            System.out.print("2.Bad: ");
            System.out.println(solution678
                    .checkValidString("(*)"));
        }
        if (solution678.checkValidString("(*))")) {
            System.out.println("3.Good");
        } else {
            System.out.print("3.Bad: ");
            System.out.println(solution678
                    .checkValidString("(*))"));
        }
        if (!solution678.checkValidString("(")) {
            System.out.println("4.Good");
        } else {
            System.out.print("4.Bad: ");
            System.out.println(solution678
                    .checkValidString("("));
        }
        if (solution678.checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()")) {
            System.out.println("5.Good");
        } else {
            System.out.print("5.Bad: ");
            System.out.println(solution678
                    .checkValidString("((((()(()()()*()(((((*)()*(**(())))))(())()())(((())())())))))))(((((())*)))()))(()((*()*(*)))(*)()"));
        }
        if (solution678.checkValidString("(((((*)))**")) {
            System.out.println("6.Good");
        } else {
            System.out.print("6.Bad: ");
            System.out.println(solution678
                    .checkValidString("(((((*)))**"));
        }
        if (!solution678.checkValidString("(*()))*(")) {
            System.out.println("7.Good");
        } else {
            System.out.print("7.Bad: ");
            System.out.println(solution678
                    .checkValidString("(*()))*("));
        }
    }
}
