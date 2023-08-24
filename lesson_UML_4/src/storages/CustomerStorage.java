package storages;
/**
 * База пользователей
 */
import models.Customer;
import provider.CashProvider;
import provider.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerStorage {
    private static final CustomerStorage storage;

    static {
        storage = new CustomerStorage();
    }

    private List<Customer> customers;

    private CustomerStorage() {
        this.customers = new ArrayList<>();
        Customer customer1 = new Customer(1, "stan", null, null);
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(1,5.5,1, LocalDateTime.parse("2018-12-30T19:34:50.63"),
                true, "nik"));
        tickets.add(new Ticket(2,6.5,1, LocalDateTime.parse("2018-12-30T19:34:50.63"),
                true, "nik"));
        Customer customer2 = new Customer(2, "nik", tickets, null);
        customers.add(customer1);
        customers.add(customer2);
    }

    public static CustomerStorage storage() {
        return storage;
    }

    public List<Customer> customers() {
        return customers;
    }


}
