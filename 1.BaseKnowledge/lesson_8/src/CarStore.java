import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
            System.out.println("Извините, но магазин не может вместить ещё один автомобиль");
        }
    }

    public void remove(Car car) {
        ListIterator<Car> iterator = catalog.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(car)) {
                iterator.remove();
                System.out.println("Удаление выполенено");
            }
        }
  //      throw new Exception("Извините, но нет автомобилей для удаления");
    }

    public List<Car> getCatalog() {
        return catalog;
    }

    public int getNumberCars() {
        return catalog.size();
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
