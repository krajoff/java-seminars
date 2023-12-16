package ru.geekbrains.lesson4.homework;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.lesson4.models.Course;

/**
 * Задание
 * =======
 * Создайте базу данных (например, SchoolDB).
 * В этой базе данных создайте таблицу Courses с полями id (ключ), title, и duration.
 * Настройте Hibernate для работы с вашей базой данных.
 * Создайте Java-класс Course, соответствующий таблице Courses, с необходимыми аннотациями Hibernate.
 * Используя Hibernate, напишите код для вставки, чтения, обновления и удаления данных в таблице Courses.
 * Убедитесь, что каждая операция выполняется в отдельной транзакции.
 */

public class Program {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Создание сессии
        Session session = sessionFactory.getCurrentSession();

        try {
            // Начало транзакции
            session.beginTransaction();

            // Создание объекта
            Course course = Course.create();


            // Сохранение объекта в базе данных
            session.save(course);
            System.out.println("Object course save successfully");

            // Чтение объекта из базы данных
            Course retrievedStudent = session.get(Course.class, course.getId());
            System.out.println("Object course retrieved successfully");
            System.out.println("Retrieved course object: " + retrievedStudent);

            // Обновление объекта
            retrievedStudent.updateTitle();
            retrievedStudent.updateDuration();
            session.update(retrievedStudent);
            System.out.println("Object course update successfully");

            // Удаление объекта
            //session.delete(retrievedStudent);
            //System.out.println("Object student delete successfully");

            session.getTransaction().commit();
            System.out.println("Transaction commit successfully");
        } finally {
            // Закрытие фабрики сессий
            sessionFactory.close();
        }
    }
}
