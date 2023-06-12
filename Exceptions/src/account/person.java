package account;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.join;

public class person {
    private String name;
    private String surname;
    private String patronymic;
    private Date birthday;
    String patternBirthday = "dd.MM.yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternBirthday);
    private String phone;
    private String sex;

    public person(String name, String surname, String patronymic,
                  String birthday, String phone, String sex) throws ParseException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = simpleDateFormat.parse(birthday);
        this.phone = phone;
        this.sex = sex;
    }

    public void writeToFile(){
        String line = String.join("><",
                surname,
                name,
                patronymic,
                simpleDateFormat.format(birthday),
                phone,
                sex);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(surname, true))) {
            writer.write("<"+line+">"+"\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthday=" + simpleDateFormat.format(birthday) +
                ", phone=" + phone +
                ", sex=" + sex +
                '}';
    }


}
