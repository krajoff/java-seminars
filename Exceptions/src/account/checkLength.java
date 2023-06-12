package account;

public class checkLength extends RuntimeException{
    public static void checkLength(String[] data, int count) {
        if (data.length > count) {
            throw new RuntimeException("You entered more data than necessary");
        } else if (data.length < count) {
            throw new RuntimeException("You entered less data than necessary");
        }
    }
}
