/**
 * Реализация класса Car с заданием основных свойств
 */
public class Car extends Vehicle {

    public Car(String company, String model, Engine engine, String color,
               String type, int wheel, double volume, int capacity, Gearbox gearbox) {
        super(company, model, engine, color, type, wheel, volume, capacity, gearbox);
    }

    public Car() {
        super("Ford", "Focus",
                new PetrolEngine(), "black",
                "sedan", 4, 1.2, 2, Gearbox.Auto);
    }

    /**
     * Определение метода start от интерфейса Engine
     */
    public void start() {
        super.start();
    }

    @Override
    public void setCapacity(int capacity) {
        if (capacity > 8 || capacity < 2) {
            throw new RuntimeException("Не верное число возможных пассажиров");
        } else {
            super.setCapacity(capacity);
        }
    }
}
