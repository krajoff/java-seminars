public class Main {
    public static void main(String[] args) {
        Car car1 = new Car("BMW", "черный", 2011, 1_000_000, 120);
        System.out.println(car1);
        car1.power();
        car1.getState();
        car1.power();
        car1.goTo("Мариинская улица дом 1");
        Car car2 = new Car("BMW", "черный", 2001, 1_000_000, 120);
        System.out.println();
        if (car2.equals(car1)) {
            System.out.println("Автомибили одинаковые");
        } else {
            System.out.println("Автомибили разные");
        }
    }
}
