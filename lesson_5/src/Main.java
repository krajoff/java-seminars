import java.util.*;

public class Main {
    public static void main(String[] args) {
//        ex0();
        String list = "Иван Иванов, Светлана Петрова, Кристина Белова, Анна Мусина, Анна Крутова," +
                " Иван Юрин, Петр Лыков, Павел Чернов, Петр Чернышов, Мария Федорова, Марина Светлова, Мария Савина," +
                " Мария Рыкова, Марина Лугова, Анна Владимирова, Иван Мечников, Петр Петин, Иван Ежов";
        ex1(list);
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
    static void ex1(String list) {
        String[] coworkers = list.split(", ");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        for (String worker : coworkers) {
            String name = worker.split(" ")[0];
            if (treeMap.containsKey(name)) {
                int cnt = treeMap.get(name);
                cnt++;
                treeMap.put(name, cnt);
            } else {
                treeMap.put(name, 1);
            }
        }

        // Первый вариант
        TreeMap<String, Integer> sortedByTreeMap = new TreeMap<>((o1, o2) -> {
            Integer o1Value = treeMap.get(o1);
            Integer o2Value = treeMap.get(o2);
            return o1Value.compareTo(o2Value);
        });

        //Второй вариант
        List<Map.Entry<String, Integer>> listtemp = new LinkedList<>(treeMap.entrySet());
        Collections.sort(listtemp, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        Map<String, Integer> sortedlinkedmap = new LinkedHashMap<>(listtemp.size());
        for (Map.Entry<String, Integer> entry : listtemp) {
            sortedlinkedmap.put(entry.getKey(), entry.getValue());
        }


        System.out.println("Оригинальный TheeMap: " + treeMap);
        System.out.println("Отсортированный TreeMap через LinkedList: " + sortedlinkedmap);
        sortedByTreeMap.putAll(treeMap);
        System.out.println("Отсортированный при помощи TreeMap: " + sortedByTreeMap);
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
