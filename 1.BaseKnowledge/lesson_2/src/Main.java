import netscape.javascript.JSObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) {
        String strJSON = "{{\"фамилия\":\"Иванов\", \"оценка\":\"5\", \"предмет\":\"Математика\"},\n" +
                "{\"фамилия\":\"Петрова\",\"оценка\":\"4\",\"предмет\":\"Информатика\"},\n" +
                "{\"фамилия\":\"Краснов\",\"оценка\":\"5\",\"предмет\":\"Физика\"}}";
        StringBuilder stringBuilder = ex0(strJSON);
        ex1(stringBuilder, "output.txt");
        int [] array = {11, 3, 14, 16, 7};
        ex2(array, "bubble.txt");
    }

    /*
     * Дана json строка { { "фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":
     * "Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}} Задача написать метод(ы), который распарсить строку
     * и выдаст ответ вида: Студент Иванов получил 5 по предмету Математика. Студент Петрова получил 4 по предмету Информатика.
     * Студент Краснов получил 5 по предмету Физика. Используйте StringBuilder для подготовки ответа
     */
    static StringBuilder ex0(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] strStudent = str.split("},");
        for (int i = 0; i < strStudent.length; i++) {
            String[] strInfoStudent = strStudent[i].split(",");
            for (int j = 0; j < strInfoStudent.length; j++) {
                String[] strInfoByPart = strInfoStudent[j].split(":");
                strInfoStudent[j] = strInfoByPart[1].replace("\"", "");
                strInfoStudent[j] = strInfoStudent[j].replace("}", "");
            }
            stringBuilder = stringBuilder.append("Студент " + strInfoStudent[0] + " получил " + strInfoStudent[1] + " по предмету " + strInfoStudent[2] + ".\n");
        }
        System.out.println(stringBuilder);
        return stringBuilder;
    }

    // Создать метод, который запишет результат работы в файл Обработайте исключения и запишите ошибки в лог файл
    static void ex1(StringBuilder stringBuilder, String filePath) {
        Logger logger = Logger.getAnonymousLogger();
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;

        try {
            fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);

        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            fileWriter.write(stringBuilder.toString());
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            e.printStackTrace();
        }
    }

    // Реализуйте алгоритм сортировки пузырьком числового массива, результат после каждой итерации запишите в лог-файл.
    static void ex2(int[] array, String filePath){
        boolean isSorted = false;
        int buf;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < array.length-1; i++) {
                if(array[i] > array[i+1]){
                    isSorted = false;
                    buf = array[i];
                    array[i] = array[i+1];
                    array[i+1] = buf;
                    try (FileWriter fileWriter = new FileWriter(filePath, true)) {
                        fileWriter.write(Arrays.toString(array));
                        fileWriter.write("\n");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
