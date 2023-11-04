package second;

public class Main {
    public static void main(String[] args) {
        multiply(2);
    }

    public static void multiply(int number) {
        for (int i = 1; i < 10; i++) {
            System.out.println(number + " * " + i + " = " + number * i);
        }
    }
}
