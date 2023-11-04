/**
 * Реализация класса Vehicle с заданием основных свойств
 */
public class Vehicle {
    private String company;
    private String model;
    private Engine engine;
    private String color;
    private String type;
    private int wheel;
    private double volume;
    private int capacity;
    enum Headlights {On, Off};
    private Headlights headlights;

    enum Gearbox {
        Auto, Robot, Manual
    }
    private Gearbox gearbox;


    public Vehicle(String company, String model, Engine engine, String color,
               String type, int wheel, double volume, int capacity, Gearbox gearbox) {
        this.company = company;
        this.model = model;
        this.engine = engine;
        this.color = color;
        this.type = type;
        this.wheel = wheel;
        this.volume = volume;
        this.capacity = capacity;
        this.gearbox = gearbox;
    }

    public Vehicle(){
        this("","",null,"","",0,0,1,null);
    }

    public void start() {
        this.engine.start();
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
