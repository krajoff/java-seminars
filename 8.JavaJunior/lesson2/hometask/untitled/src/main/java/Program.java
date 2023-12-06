import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Задача 1:
 * Создайте абстрактный класс "Animal" с полями "name" и "age".
 * Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat")
 * с уникальными полями и методами.
 * Создайте массив объектов типа "Animal" и с использованием Reflection API
 * выполните следующие действия:
 * Выведите на экран информацию о каждом объекте.
 * Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.
 * <p>
 * Дополнительная задача:
 * <p>
 * Доработайте метод генерации запроса на удаление объекта из таблицы
 * БД (DELETE FROM <Table> WHERE ID = '<id>')
 * В классе QueryBuilder который мы разработали на семинаре.
 */

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<Object> animals = new ArrayList<>();

        Class<?> dogClass = Class.forName("Dog");
        Class<?> catClass = Class.forName("Cat");
        Class<?> animalClass = Class.forName("Animal");

        Constructor[] constructorsDog = dogClass.getConstructors();
        Constructor[] constructorsCat = catClass.getConstructors();

        animals.add(constructorsDog[0].newInstance("Bobik", 15, 35));
        animals.add(constructorsCat[0].newInstance("Kitty", 10, 5));
        animals.add(constructorsDog[0].newInstance("Spyk", 20, 45));

        animals.forEach(System.out::println);

        Method method = animalClass.getDeclaredMethod("makeSound");

        for (int i = 0; i < animals.size(); i++) {
            try {
                method.invoke(animals.get(i));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }


        }


    }

}
