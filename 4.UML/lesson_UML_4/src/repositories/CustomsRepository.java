package repositories;
/**
 * Интерфейс поведения объекта пользователь, предоставляющего данные о нем
 */
import models.Customer;
import provider.Ticket;

import java.util.List;


public interface CustomsRepository {
    List<Customer> findAll();

    void save(Customer customer);

    boolean isExist(String name);

    List<Ticket> tickets(String name);
}
