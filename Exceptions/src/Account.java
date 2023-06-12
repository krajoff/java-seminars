import account.*;

import java.io.IOException;
import java.text.ParseException;


public class Account {
    public static void main(String[] args) throws ParseException {
        String initial = "Николай Яшин Валерьевич 28.02.1999 892255542164 m";
        person person = validator.push(initial);
        System.out.println(person);
        person.writeToFile();

    }


}
