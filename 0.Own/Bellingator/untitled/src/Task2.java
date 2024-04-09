import java.util.*;

/**
 * Данная таблица представлена в формате
 * List<Map<String,String>>, где каждый элемент list
 * - строка, key в map - название столбца, value в map - значение ячейки
 * Задача:
 * - вывести имена всех сотрудников, младше 30.
 * - вывести имена всех сотрудников, получающих зарплату в рублях.
 * - вывести средний возраст всех сотрудников.
 */

enum Сolumn {
    name, age, position, salary
}

class Employee {
    Map<String, String> employee;

    public Employee(String name, String ages, String position, String salary) {
        employee = new HashMap<>();
        employee.put(Сolumn.name.name(), name);
        employee.put(Сolumn.age.name(), ages);
        employee.put(Сolumn.position.name(), position);
        employee.put(Сolumn.salary.name(), salary);

    }


    public String getName() {
        return employee.get(Сolumn.name.name());
    }

    public String getAge() {
        return employee.get(Сolumn.age.name());
    }

    public String getPosition() {
        return employee.get(Сolumn.position.name());
    }

    public String getSalary() {
        return employee.get(Сolumn.salary.name());
    }

    public String toString() {
        return "Employee{" +
                Сolumn.name.name() + ": " + this.getName() + "; " +
                Сolumn.age.name() + ": " + this.getAge() + "; " +
                Сolumn.position.name() + ": " + this.getPosition() + "; " +
                Сolumn.salary.name() + ": " + this.getSalary() +
                "}";
    }

}


class Employees {

    private final List<Employee> employees;

    private void initEmployees() {
        employees.add(new Employee("Кирилл", "26",
                "Middle java dev", "150000 руб."));
        employees.add(new Employee("Виталий", "28",
                "Senior java automation QA", "2000$"));
        employees.add(new Employee("Александр", "31",
                "junior functional tester", "50000 руб"));
        employees.add(new Employee("Дементий", "35",
                "dev-ops", "1500$"));
    }

    public Employees() {
        employees = new ArrayList<>();
        initEmployees();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<String> getNamesYoungerThanAges(int maxAge) {
        return getEmployees()
                .stream()
                .filter(e -> Integer.parseInt(e.getAge()) < maxAge)
                .map(Employee::getName)
                .toList();
    }

    public List<String> getNamesGetSalaryByRubles() {
        return getEmployees()
                .stream()
                .filter(e -> e.getSalary().contains("руб"))
                .map(Employee::getName)
                .toList();
    }

    public double averageAgesAllEmployees() {
        int sum = getEmployees()
                .stream()
                .map(Employee::getAge)
                .map(Integer::valueOf)
                .mapToInt(Integer::intValue)
                .sum();
        return (double) sum / getEmployees().size();
    }

    @Override
    public String toString() {
        return "Employees" + employees;
    }

}


public class Task2 {
    public static void main(String[] args) {
        Employees employees = new Employees();
        System.out.println(employees.getNamesYoungerThanAges(30));
        System.out.println(employees.getNamesGetSalaryByRubles());
        System.out.println(employees.averageAgesAllEmployees());
    }
}