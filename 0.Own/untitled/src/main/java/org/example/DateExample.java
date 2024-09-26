package org.example;

import java.util.Calendar;
import java.util.Date;

public class DateExample {

    public static void main(String[] args) {
        Date now = new Date();

        // Получение начала месяца
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfMonth = calendar.getTime();

        // Получение конца месяца
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Устанавливаем последний день месяца
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date endOfMonth = calendar.getTime();

        // Вывод результатов
        System.out.println("Текущая дата: " + now);
        System.out.println("Начало месяца: " + startOfMonth);
        System.out.println("Конец месяца: " + endOfMonth);

        Week week = new Week();
        week.getDayofWeek("13");


        Trie phoneDirectory = new Trie();

        // Добавление клиентов
        phoneDirectory.addClient("1234567890", "Client A");
        phoneDirectory.addClient("1234567890", "Client С");

        phoneDirectory.addClient("0987654321", "Client B");

        // Поиск клиентов
        System.out.println(phoneDirectory.findClient("1234567890")); // Output: Client A
        System.out.println(phoneDirectory.findClient("1111111111")); // Output: null

        // Удаление клиента
        phoneDirectory.deleteClient("1234567890");
        System.out.println(phoneDirectory.findClient("1234567890")); // Output: null


    }

}

