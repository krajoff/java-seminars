package account;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class validator {
    public static person push(String initial) throws ParseException {
        String[] data = initial.split(" ");
        String name = data[0], surname = data[1], patronymic = data[2];
        String birthday = data[3], phone = data[4], sex = data[5];
        validLength(data, 6);
        validDateFormat(birthday);
        validPhone(phone);
        validSex(sex);
        return new person(name, surname, patronymic, birthday, phone, sex);
    }

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public static void validLength(String[] data, int count) {
        if (data.length > count) {
            throw new RuntimeException("You entered more data than necessary");
        } else if (data.length < count) {
            throw new RuntimeException("You entered less data than necessary");
        }
    }

    public static void validDateFormat(String date) {
        try {
            simpleDateFormat.parse(date);
            simpleDateFormat.setLenient(false);
        } catch (ParseException e) {
            throw new RuntimeException("You entered incorrect date format " +
                    "(right format dd.MM.yyyy): " + date);
        }
    }
    public static void validPhone(String phone){
        if (!phone.matches("[0-9]+")) {
            throw new RuntimeException("You entered incorrect phone number " +
                    "(it doesn't includes digits only)");
        }
        if (phone.length() < 3) {
            throw new RuntimeException("You entered incorrect phone number " +
                    "(it is less than 3 digits)");
        }
    }

    public static void validSex(String sex) {
        if (!sex.equals("m") && !sex.equals("f")) {
            throw new RuntimeException("You entered incorrect sex");
        }
    }

}
