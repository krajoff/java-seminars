package seminar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main1 {
    /*
    В рамках выполнения задачи необходимо:
    1. Создайте коллекцию мужских и женских имен с помощью интерфейса List
    2. Отсортируйте коллекцию в алфавитном порядке
    3. Отсортируйте коллекцию по количеству букв в слове
    4. Разверните коллекцию
     */

    public static void main(String[] args) {
        List<String> list = generateList();
        System.out.println(list);
        sortByAlphabet(list);
        System.out.println(list);
        sortByLength(list);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);
    }

    private static void sortByLength(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
    }

    private static void sortByAlphabet(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    static List<String> generateList(){
        List<String> list = new ArrayList<>();
        list.add("Константин");
        list.add("Василий");
        list.add("Светлана");
        list.add("Анна");
        list.add("Иван");
        list.add("Семен");
        return list;
    }
}
