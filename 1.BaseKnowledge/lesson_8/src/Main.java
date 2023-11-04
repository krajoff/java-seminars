public class Main {
    public static void main(String[] args) throws Exception {
        CarStore store = new CarStore(8);
        store.add(new Car("BMW", "черный", 2011, 1_000_000, 120));
        store.add(new Car("BMW", "черный", 2011, 1_300_000, 120));
        store.add(new Car("Fiat", "серый", 2021, 3_000_000, 130));
        store.add(new Car("Suzuki", "белый", 2011, 500_000, 100));
        store.add(new Car("BMW", "черный", 2001, 300_000, 90));
        store.add(new Car("BMW", "черный", 2004, 1_000_000, 120));
        store.add(new Car("BMW", "черный", 2001, 300_000, 100));
        store.add(new Car("Fiat", "серый", 2021, 5_000_000, 150));
        store.add(new Car("Suzuki", "белый", 2010, 500_000, 110));
        System.out.printf("Емкость магазина: %d \n", store.getCapacity());
        System.out.printf("Количество автомобилей в магазине: %d \n", store.getNumberCars());
        Car car1 = new Car("BMW", "черный", 2011, 1_000_000, 120);
        store.remove(car1);
        System.out.printf("Количество автомобилей в магазине: %d \n", store.getNumberCars());

        try {
            System.out.println(store.getCarByYear(2011));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
