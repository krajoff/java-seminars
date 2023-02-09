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
        int laptopNumbers = 10000;
        Set laptopsSet = laptops(laptopNumbers);
        System.out.println(laptopsSet);
        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap(criteriaMap);
        System.out.println(criteriaMap);
        Set<Laptop> fl = filteredLaptops(criteriaMap, laptopsSet);
        System.out.println(fl);
        System.out.println();
        System.out.printf("Найдено %d ноутбуков по текущему критерию", fl.size());

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

    static Map<Integer, String> criteriaMap(Map<Integer, String> criteriaMap) {
        Map<Integer, String> greeting = new HashMap<>();
        greeting.put(1, "Введите минимальный объем ОЗУ [Гб]: ");
        greeting.put(2, "Введите минимальный объем диска [Гб]: ");
        greeting.put(3, "Введите операционную систему: ");
        greeting.put(4, "Введите необходимый цвет ноубука: ");
        System.out.print("Введите критерий фильтрации: 1 - ОЗУ, 2 - Объем ЖД, 3 - Операционная система, 4 - Цвет: ");
        Scanner scanner = new Scanner(System.in);
        int criterion = scanner.nextInt();
        if (criterion < 5 && criterion > 0) {
            System.out.print(greeting.get(criterion));
            scanner.nextLine();
            criteriaMap.put(criterion, scanner.nextLine());
            System.out.print("Добавляем критерии фильтрации да/yes или нет/no? ");
            String answer = scanner.nextLine();
            if (answer.equals("yes") || answer.equals("да")) {
                criteriaMap(criteriaMap);
            }
        } else {
            System.out.println("Недопустимый номер критерия");
        }
        return criteriaMap;
    }

    static Set<Laptop> filteredLaptops(Map<Integer, String> criteriaMap, Set laptopsSet) {
        Set<Laptop> filteredLaptops = new HashSet<>();
        Iterator<Laptop> iterator = laptopsSet.iterator();
        while (iterator.hasNext()) {
            Laptop current = iterator.next();
            int cnt = 0;
            for (Map.Entry<Integer, String> entry : criteriaMap.entrySet()) {
                switch (entry.getKey()) {
                    case 1:
                        if (current.memoryPrimary < Integer.parseInt(entry.getValue())) {
                            cnt++;
                        }
                        break;
                    case 2:
                        if (current.memoryHDD < Integer.parseInt(entry.getValue())) {
                            cnt++;
                        }
                        break;
                    case 3:
                        if (!current.systemName.equals(entry.getValue())) {
                            cnt++;
                        }
                        break;
                    case 4:
                        if (!current.color.equals(entry.getValue())) {
                            cnt++;
                        }
                        break;
                }

            }
            if (cnt == 0) {
                filteredLaptops.add(current);
            }
        }
        return filteredLaptops;
    }
}
