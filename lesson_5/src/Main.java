import java.util.*;

public class Main {
    public static void main(String[] args) {
        ex0();
        ex1();
    }

    /*
    Реализуйте структуру телефонной книги с помощью HashMap, учитывая, что 1 человек может иметь несколько телефонов.
     */
    static void ex0() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("Николай", Arrays.asList("+7-921-311-56-16", "+7-812-311-56-66"));
        map.put("Елена", List.of("+7-921-098-10-13"));
        map.put("Алена", List.of("+7-922-222-55-16"));
        map.put("Павел", List.of("+7-902-222-52-00"));
        map.put("Илья", List.of("+7-912-951-67-98"));
        System.out.print("Введите имя: ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Введите телефон: ");
        String phone = scanner.nextLine();
        if (map.containsKey(name)) {
            List<String> list = new ArrayList<>(map.get(name));
            list.add(phone);
            map.put(name, list);
        } else {
            map.put(name, List.of(phone));
        }
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry);
        }

    }

    /*
    Пусть дан список сотрудников: Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова,
    Иван Юрин, Петр Лыков, Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина,
    Мария Рыкова, Марина Лугова, Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов. Написать программу,
    которая найдет и выведет повторяющиеся имена с количеством повторений. Отсортировать по убыванию популярности.
    Для сортировки использовать TreeMap.
    */
    static void ex1() {
//        TreeMap<List<Integer>, List<String>> treeMap = new TreeMap<>();
//        for (int i = 0; i)
    }

    /*
    Реализовать алгоритм пирамидальной сортировки (HeapSort)
    */
    static void ex2() {

    }

    /*
    На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
     */
    static void ex3() {

    }
}
