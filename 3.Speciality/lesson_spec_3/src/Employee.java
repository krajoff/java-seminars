import java.util.Objects;

public abstract class Employee implements Comparable<Employee> {

    private String firstname;

    private String surname;

    private String position;

    private Double salary;

    public Employee(String firstname, String surname, String position) {
        this.firstname = firstname;
        this.surname = surname;
        this.position = position;
    }

    abstract Double calculateSalary();

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}' + '\n';
    }


    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }


    @Override
    public int compareTo(Employee e) {
        return Double.compare(getSalary(), e.getSalary());
    }
}
