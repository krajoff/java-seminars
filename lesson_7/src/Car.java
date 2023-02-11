public class Car {
    String model;
    String color;
    int year;
    double price;
    int power;

    enum State {
        On, Off
    }

    private State state;

    Car(String model, String color, int year, double price, int power) {
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
        this.power = power;
        this.state = State.Off;
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public void power() {
        if (this.state == State.Off) {
            this.state = State.On;
            System.out.println("Вы завели автомобиль");
        } else {
            this.state = State.Off;
            System.out.println("Вы заглушили автомобиль");
        }
    }

    public String getInfo() {
        return String.format("Модель: %s; год производства: %d; объем двигателя: %d", this.model, this.year, this.power);
    }

    public void getState() {
        if (this.state == State.On) {
            System.out.println("Автомобиль заведён");
        } else {
            System.out.println("Автомобиль заглушен");
        }
    }

    public void goTo(String address) {
        if (this.state == State.On) {
            System.out.printf("Автомобиль едет по адресу: %s", address);
        } else {
            System.out.printf("Автомобиль не заведен и не может следовать по маршруту: %s", address);
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        final Car other = (Car) o;
        return this.model == other.model && this.year == other.year && this.power == other.power;
    }
}
