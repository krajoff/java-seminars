public class PassengerCar extends MotorVehicle {
    private String wheel;

    PassengerCar(String fuel, int capacity, int power, int maxSpeed, String sign, String wheel) {
        super(fuel, capacity, power, maxSpeed, sign);
        this.wheel = wheel;
        this.setType("Машина");
    }

    PassengerCar() {
        super();
    }

    public String getWheel() {
        return wheel;
    }

    public void setWheel(String wheel) {
        this.wheel = wheel;
    }

    @Override
    public void setCapacity(int capacity) {
        if (capacity>1 && capacity<8) {
            super.setCapacity(capacity);
        } else {
            System.out.println("Недопустимое количество людей в легковом автомобиле");
        }
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "wheel='" + wheel + '\'' +
                '}' + " {" + personList + "}" ;
    }
}
