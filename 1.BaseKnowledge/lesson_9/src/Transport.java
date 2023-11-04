import java.util.ArrayList;
import java.util.List;

public class Transport {
    private String type;
    private String fuel;
    private int capacity;
    private int power;
    private int maxSpeed;
    private int id;
    static int cnt;
    enum State {
        On, Off
    }
    private State state;
    List<Person> personList = new ArrayList<>();
    Transport(String fuel, int capacity, int power, int maxSpeed) {
        this.type = "Транспорт";
        this.fuel = fuel;
        this.capacity = capacity;
        this.power = power;
        this.maxSpeed = maxSpeed;
        this.id = cnt;
        this.state = State.Off;
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

    public void power() {
        if (this.state == State.Off) {
            this.state = State.On;
            System.out.println(this.type + " заведен(о)");
        } else {
            this.state = State.Off;
            System.out.println(this.type + " заглушен(о)");
        }
    }

    public void getState() {
        if (this.state == State.On) {
            System.out.println("Заведён");
        } else {
            System.out.println("Заглушен");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
