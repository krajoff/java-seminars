import java.util.ArrayList;
import java.util.List;

public class Main {
//    Создать иерархию родительский и дочернии классы для Транспорта
//    Например: Транспорт - > Автомобиль - > Автобус
//    Транспорт - > Автомобиль - > Легковой
//    Используем наследование, инкапсуляцию
    static public void main (String[] args){
        Transport tr = new Transport();
        System.out.println(tr);
        PassengerCar car = new PassengerCar();
        car.setCapacity(2);
        car.add(new Person("Bob", "Fry"));
        car.add(new Person("Mike", "Samuil"));
        car.add(new Person("Nike", "Samuil"));
        car.toString();
        car.power();
        car.power();
        System.out.println(car);
        MotorVehicle vehicle = new MotorVehicle("disel", 4, 120, 240, "u655px");
        System.out.println(vehicle);
        Bus bus = new Bus();
        System.out.println(bus);
        List<String> list = new ArrayList<>();

    }
}
