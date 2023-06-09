package tasks;

/**
 * Если необходимо, исправьте данный код
 */
public class task5 {
    public static void tr(int[] intArray) {
        try {
            int d = 0;
            double catchedRes1 = intArray[8] / d;
            System.out.println("catchedRes1 = " + catchedRes1);
        } catch(ArithmeticException | ArrayIndexOutOfBoundsException e) {
            System.err.println("Catching exception: " + e);
        }
    }
}
