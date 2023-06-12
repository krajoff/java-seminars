package account;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class checkDateFormat {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void isValid(String date) {
        try {
            simpleDateFormat.parse(date);
            simpleDateFormat.setLenient(false);
        } catch (ParseException e) {
            throw new RuntimeException("You entered incorrect date format (right format dd.MM.yyyy): " + date);
        }
    }
}
