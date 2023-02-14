import java.util.ArrayList;
import java.util.List;

public class CarStore {

    private int capacity = 10;

    private List<Car> catalog = new ArrayList<>();

    public CarStore(int capacity, List<Car> catalog) {
        this.capacity = capacity;
        this.catalog = catalog;
    }

    public CarStore(int capacity) {
        this.capacity = capacity;
        new CarStore(capacity, catalog);
    }

    public CarStore(List<Car> catalog) {
        this.catalog = catalog;
        new CarStore(capacity, catalog);
    }

    public CarStore() {
        new CarStore(capacity, catalog);
    }

    public void add(Car car) {
        if (capacity > catalog.size()) {
            catalog.add(car);
        } else {
            System.out.println("Sorry, the catalog is full");
        }
    }

    public List<Car> getCatalog() {
        return catalog;
    }

    public int getCapacity() {
        return capacity;
    }

    public Car getCarByYear(int year) throws Exception {
        for (Car car : catalog) {
            if (car.getYear() == year) {
                return car;
            }
        }
        throw new Exception("Нет подходящих автомобилей");
    }

}
