package account;

public class checkSex {
    public static void isValid(String sex) {
        if (!sex.equals("m") && !sex.equals("f")) {
            throw new RuntimeException("You entered incorrect sex");
        }
    }
}
