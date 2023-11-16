package Catalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Catalog {
    List<Employee> catalog;

    public Catalog() {
        this.catalog = new ArrayList<>();
    }

    public void add(Employee employee) {
        catalog.add(employee);
    }

    public void remove(Employee employee) {
        catalog.remove(employee);
    }

    public void remove(Integer id) {
        catalog.remove(id);
    }

    public Catalog searchByExp(Double exp) {
        Catalog result = new Catalog();
        Iterator<Employee> iterator = catalog.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getExperience().equals(exp)) {
                result.add(employee);
            }
        }
        return result;
    }

    public List<String> searchPhoneByName(String name) {
        List<String> result = new ArrayList<>();
        Iterator<Employee> iterator = catalog.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getName().equals(name)) {
                result.add(employee.getPhone());
            }
        }
        return result;
    }

    public Catalog searchById(Integer id) {
        Catalog result = new Catalog();
        Iterator<Employee> iterator = catalog.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId().equals(id)) {
                result.add(employee);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "catalog=" + catalog +
                '}';
    }
}
