// Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
// Создать множество ноутбуков. Написать метод, который будет запрашивать у пользователя критерий
// (или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. Критерии фильтрации можно хранить в Map.
// Например: “Введите цифру, соответствующую необходимому критерию:
// 1 - ОЗУ
// 2 - Объем ЖД
// 3 - Операционная система
// 4 - Цвет …
// Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.
// Отфильтровать ноутбуки из первоначального множества и вывести проходящие по условиям.

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int number = 2;
        Set laptopsSet = laptops(number);
        System.out.println(laptopsSet);
        firstInput();
    }

    static Set laptops(int number) {
        Random random = new Random();
        String[] companies = new String[]{"Asus", "MSI", "Dell", "Samsung", "Acer"};
        String[] colors = new String[]{"чёрный", "синий", "белый", "серебренный", "красный"};
        String[] systemNames = new String[]{"Windows 10 x64", "Windows 11 x64", "Windows 7", "Ubuntu x64"};
        double[] displaySizes = new double[]{10, 11, 14, 15.4, 17};
        Set<Laptop> laptops = new HashSet<>();
        for (int i = 0; i < number; i++) {
            Laptop laptop = new Laptop();
            laptop.company = companies[random.nextInt(companies.length)];
            char Letter = (char) (random.nextInt(26) + 'a');
            laptop.model = Letter + String.valueOf(random.nextInt(100) + 100);
            laptop.color = colors[random.nextInt(colors.length)];
            laptop.memoryPrimary = 4 * (random.nextInt(4) + 1);
            laptop.memoryHDD = 250 * (random.nextInt(8) + 1);
            laptop.systemName = systemNames[random.nextInt(systemNames.length)];
            laptop.displaySize = displaySizes[random.nextInt(displaySizes.length)];
            laptops.add(laptop);
        }
        return laptops;
    }

    static void firstInput() {
        System.out.println("Введите критерий фильтрации: 1 - ОЗУ, 2 - Объем ЖД, 3 - Операционная система, 4 - Цвет: ");
        Scanner scanner = new Scanner(System.in);
        int criteria = scanner.nextInt();
        Map<Integer, List<String>> filter = new HashMap<>();
        filter.put(1, List.of("Введите минимальный объем необходимой памяти: ", ""));
        filter.put(2, List.of("Введите минимальный объем диска: ", ""));
        filter.put(3, List.of("Введите операционную систему: ", ""));
        filter.put(4, List.of("Введите необходимый цвет ноубука: ", ""));
        if (criteria < 5 && criteria > 0) {
            System.out.println(filter.get(criteria).get(0));
        } else {
            System.out.println("Недопустимый номер критерия");
        }


    }
}
