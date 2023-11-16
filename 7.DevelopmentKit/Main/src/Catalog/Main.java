package Catalog;

public class Main {
    /* Создать справочник сотрудников
    Необходимо:
    Создать класс справочник сотрудников, который содержит внутри
    коллекцию сотрудников - каждый сотрудник должен иметь следующие атрибуты:
    Табельный номер,  Номер телефона, Имя, Стаж
    Добавить метод, который ищет сотрудника по стажу (может быть список)
    Добавить метод, который возвращает номер телефона сотрудника по имени (может быть список)
    Добавить метод, который ищет сотрудника по табельному номеру
    Добавить метод добавление нового сотрудника в справочник
    */
    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        catalog.add(new Employee("Ivan",1, 3.0, "9213197221"));
        catalog.add(new Employee("Maksim",2, 3.2, "9219999999"));
        catalog.add(new Employee("Dmitry",3, 13.2, "91105005050"));
        catalog.add(new Employee("Dmitry",4, 10.2, "911051115050"));
        catalog.add(new Employee("Nikolay",5, 10.2, "9991115050"));
        System.out.println(catalog);
        System.out.println(catalog.searchByExp(10.2));
        System.out.println(catalog.searchPhoneByName("Dmitry"));
        System.out.println(catalog.searchById(5));
    }
}
