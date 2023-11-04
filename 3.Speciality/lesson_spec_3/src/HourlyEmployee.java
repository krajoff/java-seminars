public class HourlyEmployee extends Employee{

    Double hourlyRate;

    Double salary;

    public HourlyEmployee(String firstname, String surname,
                          String position, Double hourlyRate) {
        super(firstname, surname, position);
        this.hourlyRate = hourlyRate;
        setSalary(calculateSalary());
    }

    @Override
    Double calculateSalary() {
        salary = 20.8 * 8 * hourlyRate;
        return salary;
    }
}
