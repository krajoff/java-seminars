package calculator;

public class Calculator {

    public static <N extends Number> double sum(N a, N b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static <N extends Number> double multiply(N a, N b) {
        return a.doubleValue() * b.doubleValue();
    }

    public static <N extends Number> double divide(N a, N b) {
        return a.doubleValue() / b.doubleValue();
    }

    public static <N extends Number> double subtract(N a, N b) {
        return a.doubleValue() - b.doubleValue();
    }
}
