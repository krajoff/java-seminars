import java.time.LocalDateTime;
import java.time.Month;
public class Main {
// С учетом информации полученной ранее знакомимся с параметрическим
// полиморфизмом и продолжаем погружаться в ООП. Спроектировать и реализовать
// планировщик дел для работы с задачами разных приоритетов. Например:
// Должен быть класс Задачи (общий/родительский и подтипы)
// У задачи должно быть длительность, дедлайн, приоритет, тема, ответсвенный...
// — параметры на Ваше усмотрение
// ** У задачи должна быть возможность включить или выключить нотификацию
// на разные события, например, приближение дедлайна.
// Должен быть класс Календарь, в который можно добавлять Задачи.
// *** При пересечении задач по времени - выводить сообщение пользователю.
// **** Должна быть возможность выгрузки Календаря в файл (тип на Ваше усмотрение)
// **** Файл должен содержать следующие данные: id, дату добавления записи,
// время добавления записи, дедлай задачи, ФИО автора и др
    static public void main(String[] args) {
        Person person1 = new Person("Максим", "Яшин", "Альбертович");
        Person person2 = new Person("Николай", "Чёрный", "Иосифович");
        Person person3 = new Person("Хосе", "Аснар", "Эрнестович");

        CalendarTask todolist = new CalendarTask();
        todolist.add(new Task("Посадить цветы", person1,
                LocalDateTime.of(2023, Month.FEBRUARY, 22, 8, 00, 00),
                LocalDateTime.of(2023, Month.FEBRUARY, 23, 7, 59, 00)));

        todolist.add(new Task("Полить цветы", person1,
                LocalDateTime.of(2023, Month.FEBRUARY, 23, 7, 58, 00),
                LocalDateTime.of(2023, Month.FEBRUARY, 23, 8, 15, 00)));

        todolist.add(new Task("Прополоть цветы", person3,
                LocalDateTime.of(2023, Month.FEBRUARY, 23, 8, 10, 00),
                LocalDateTime.of(2023, Month.FEBRUARY, 23, 8, 20, 00)));

        System.out.println(todolist);
        try {
            todolist.toSave("new.csv");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
