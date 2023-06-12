import account.*;

import java.text.ParseException;


public class Account {
    public static void main(String[] args) throws ParseException {
        String str1 = "Николай Яшин Валерьевич 28.02.1999 892255542164 m";
        String str2 = "Евгений Яшин Валерьевич 28.02.1999 892255542164 m";

        person person1 = validator.push(str1);
        person1.writeToFile();
        person person2 = validator.push(str2);
        person2.writeToFile();


    }


}
