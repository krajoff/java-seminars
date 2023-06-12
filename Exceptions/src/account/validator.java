package account;

import java.text.ParseException;

public class validator {
    public static person push(String initial) throws ParseException {
        String[] data = initial.split(" ");
        String name = data[0], surname = data[1], patronymic = data[2];
        String birthday = data[3], phone = data[4], sex = data[5];
        checkLength(data, 6);
        checkDateFormat.isValid(birthday);
        checkPhone.isValid(phone);
        checkSex.isValid(sex);
        return new person(name,surname,patronymic,birthday,phone, sex);
    }
}
