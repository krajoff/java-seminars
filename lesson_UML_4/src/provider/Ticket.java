package provider;

import java.time.LocalDateTime;

/**
 * Класс билетов, где билету присваивается основные атрибуты
 */
public class Ticket {
    public long rootNumber;
    public double price;
    public int place;
    public LocalDateTime data;
    public boolean isValid;
    public String nameCustomer;

    public Ticket(long rootNumber, double price, int place, LocalDateTime data, boolean isValid, String nameCustomer) {
        this.rootNumber = rootNumber;
        this.price = price;
        this.place = place;
        this.data = data;
        this.isValid = isValid;
        this.nameCustomer = nameCustomer;
    }

    @Override
    public String toString() {
        return "rootNumber=" + this.rootNumber +
               " data=" + this.data +
               " price=" + this.price;
    }
}
