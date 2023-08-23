package repositories;

import models.Customer;

import java.util.List;


public interface CustomsRepository {
    List<Customer> findAll();

    void save(Customer customer);

    boolean isExist(String name);
}
