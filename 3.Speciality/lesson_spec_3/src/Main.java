import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> company = new ArrayList<>();
        company.add(new FullTimeEmployee("Mike", "Johnson", "Seller", 55.0));
        company.add(new FullTimeEmployee("John", "Johnson", "Director", 305.0));
        company.add(new HourlyEmployee("Peter", "Goff", "Engineer", 1.0));
        System.out.println(company);
        Collections.sort(company);
        for(Employee empoyee:company){
            System.out.println(empoyee);
        }
        System.out.println(company);

    }
}
