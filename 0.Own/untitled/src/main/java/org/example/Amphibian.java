package org.example;

public class Amphibian {
    private Car car;
    private Boat boat;

    public Amphibian(Car car, Boat boat) {
        this.car = car;
        this.boat = boat;
    }

    public void drive() {
        car.drive();
    }

    public void sail() {
        boat.sail();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}