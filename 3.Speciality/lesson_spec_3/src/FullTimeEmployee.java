public class FullTimeEmployee extends Employee{

    Double salary;

    public FullTimeEmployee(String firstname, String surname,
                            String position, Double salary) {
        super(firstname, surname, position);
        setSalary(salary);
    }

    @Override
    public Double calculateSalary() {
        return salary;
    }
}
