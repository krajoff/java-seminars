package Core.Model;

import java.util.*;

public class InfixToPostfix {
    public static ArrayList<String> toPostfix(String expression) {
        ArrayList<String> result = new ArrayList<>();
        Deque<String> stack = new ArrayDeque<>();
        String operator = "*/+-=()^";
        StringTokenizer tokenizer = new StringTokenizer(expression, operator, true);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            try {
                double digit = Double.parseDouble(token);
                result.add(Double.toString(digit));
            } catch (NumberFormatException ex) {
                if (token.equals("("))
                    stack.push(token);
                else if (token.equals(")")) {
                    while (!stack.isEmpty()
                            && !stack.peek().equals("(")) {
                        result.add(stack.peek());
                        stack.pop();
                    }
                    stack.pop();
                } else {
                    while (!stack.isEmpty()
                            && getRank(token) <= getRank(stack.peek())) {
                        result.add(stack.peek());
                        stack.pop();
                    }
                    stack.push(token);
                }
            }
        }
        while (!stack.isEmpty()) {
            result.add(stack.peek());
            stack.pop();
        }
        return result;
    }

    public static int getRank(String operator) {
        return switch (operator) {
            case "+", "-" -> 1;
            case "*", "/" -> 2;
            case "^" -> 3;
            default -> 0;
        };
    }


    public static double calculatePostfix(ArrayList<String> list) {
        Stack<Double> operands = new Stack<>();
        for(String str : list) {
            switch (str) {
                case "+", "-", "*", "/" -> {
                    double right = operands.pop();
                    double left = operands.pop();
                    double value = 0;
                    switch (str) {
                        case "+" -> value = left + right;
                        case "-" -> value = left - right;
                        case "*" -> value = left * right;
                        case "/" -> value = left / right;
                        default -> {
                        }
                    }
                    operands.push(value);
                }
                default -> operands.push(Double.parseDouble(str));
            }
        }
        return operands.pop();
    }
}

