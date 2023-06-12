package account;

import java.util.regex.Pattern;

public class checkPhone {
    public static void isValid(String phone){
        if (!phone.matches("[0-9]+")) {
            throw new RuntimeException("You entered incorrect phone number " +
                    "(it doesn't includes digits only)");
        }
        if (phone.length() < 3) {
            throw new RuntimeException("You entered incorrect phone number " +
                    "(it is less than 3 digits)");
        }
    }
}
