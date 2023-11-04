/**
 * Реализация класса Motorcycle
 */
public class Motorcycle extends Vehicle{

    @Override
    public void setCapacity(int capacity) {
        if (capacity > 3 || capacity < 1) {
            throw new RuntimeException("Не верное число возможных пассажиров");
        } else {
            super.setCapacity(capacity);
        }
    }
}
