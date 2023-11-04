package models;
/**
 * Класс пользвателей сервиса
 */
import provider.CashProvider;
import provider.Ticket;

import java.time.LocalDateTime;
import java.util.List;

public class Customer {
    public long id;
    public String name;

    public List<Ticket> tickets;
    public CashProvider cash;

    public Customer(long id, String name, List<Ticket> tickets, CashProvider cash) {
        this.id = id;
        this.name = name;
        this.tickets = tickets;
        this.cash = cash;
    }

    public boolean buyTicket(Ticket ticket) {
        return false;
    }

    public Ticket[] searchTicket(LocalDateTime DataTime) {
        return null;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
