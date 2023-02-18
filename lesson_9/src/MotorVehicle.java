public class MotorVehicle extends Transport {
    private String sign;

    MotorVehicle(String fuel, int capacity, int power, int maxSpeed, String sign) {
        super(fuel, capacity, power, maxSpeed);
        this.sign = sign;
    }

    MotorVehicle() {
        super();
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public void setCapacity(int capacity) {
        if (capacity < 120 && capacity > 1) {
            super.setCapacity(capacity);
        } else {
            System.out.println("Недопустимое количество мест");
        }
    }

    @Override
    public String toString() {
        return "MotorVehicle{" +
                "sign='" + sign + '\'' +
                '}';
    }
}
