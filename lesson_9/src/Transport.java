import java.util.ArrayList;
import java.util.List;

public class Transport {
    private String fuel;
    private int capacity;
    private int power;
    private int maxSpeed;
    private int id;
    static int cnt;

    List<Person> personList = new ArrayList<>();
    Transport(String fuel, int capacity, int power, int maxSpeed) {
        this.fuel = fuel;
        this.capacity = capacity;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.id = cnt;
        cnt++;
    }

    Transport(String fuel) {
        this(fuel, 0, 0, 0);
        this.id = cnt;
        cnt++;
    }

    Transport() {
        this(null, 0, 0, 0);
        this.id = cnt;
        cnt++;
    }

    public void add(Person person){
        if (capacity > personList.size()) {
            personList.add(person);
        } else {
            System.out.println("Извините, но в транспорте нет мест");
        }
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCnt() {
        return cnt;
    }

    public static void setCnt(int cnt) {
        Transport.cnt = cnt;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "fuel='" + fuel + '\'' +
                ", capacity=" + capacity +
                ", power=" + power +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
