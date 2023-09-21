package second.hw;

import org.junit.jupiter.api.Test;
import seminars.second.hw.Car;
import seminars.second.hw.Motorcycle;
import seminars.second.hw.Vehicle;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    Car car = new Car("Ford", "Focus", 2020);
    Motorcycle motorcycle = new Motorcycle("Ford", "Focus", 2019);

    /**
     * Проверить, что экземпляр объекта Car также является экземпляром
     * транспортного средства (используя оператор instanceof).
     **/
    @Test
    public void carIsVehicle() {
        assertInstanceOf(Vehicle.class, car);
    }

    /**
     * Проверить, что объект Car создается с 4-мя колесами.
     **/
    @Test
    public void fourWheelsCar() {
        assertEquals(4, car.getNumWheels());
    }

    /**
     * Проверить, что объект Motorcycle создается с 2-мя колесами.
     **/
    @Test
    public void twoWheelsMotorcycle() {
        assertEquals(2, motorcycle.getNumWheels());
    }

    /**
     * Проверить, что объект Car развивает скорость 60 в режиме
     * тестового вождения (используя метод testDrive()).
     **/
    @Test
    public void driveCar() {
        car.testDrive();
        assertEquals(60, car.getSpeed());
    }

    /**
     * Проверить, что объект Motorcycle развивает скорость 75 в
     * режиме тестового вождения (используя метод testDrive()).
     **/
    @Test
    public void driveMotorcycle() {
        motorcycle.testDrive();
        assertEquals(75, motorcycle.getSpeed());
    }

    /**
     * Проверить, что в режиме парковки (сначала testDrive, потом park,
     * т.е. эмуляция движения транспорта) машина останавливается (speed = 0).
     **/
    @Test
    public void parkCar() {
        car.testDrive();
        car.park();
        assertEquals(0, car.getSpeed());
    }

    /**
     * Проверить, что в режиме парковки (сначала testDrive, потом park,
     * т.е. эмуляция движения транспорта) мотоцикл останавливается (speed = 0).
     **/
    @Test
    public void parkMotorcycle() {
        motorcycle.testDrive();
        motorcycle.park();
        assertEquals(0, motorcycle.getSpeed());
    }
}