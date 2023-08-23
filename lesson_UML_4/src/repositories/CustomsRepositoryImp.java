package repositories;


import models.Customer;
import storages.CustomerStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomsRepositoryImp implements CustomsRepository {
    public List<Customer> findAll() {
        return CustomerStorage.storage().customers();
    }

    @Override
    public void save(Customer customer) {
        CustomerStorage.storage().customers().add(customer);
    }

    @Override
    public boolean isExist(String name) {
        for (Customer customer : CustomerStorage.storage().customers()) {
            if (customer.name.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
