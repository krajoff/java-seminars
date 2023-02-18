public class Bus extends MotorVehicle {

    Bus() {
        super();
    }

    @Override
    public void setCapacity(int capacity) {
        if (capacity < 120 && capacity > 8) {
            super.setCapacity(capacity);
        } else {
            System.out.println("Недопустимое количество мест");
        }
    }

    public void add() {

    }


}
