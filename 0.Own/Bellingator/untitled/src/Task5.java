import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

interface Basket {
    void addProduct(String product, int quantity);

    void removeProduct(String product);

    void updateProductQuantity(String product, int quantity);

    void clear();

    List<String> getProducts();

    int getProductQuantity(String product);
}

class Product {
    String title;
    int quantity;

    public Product(String title, int quantity) {
        this.title = title;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public int setQuantity(int quantity) {
        return this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Продукт{" +
                "Наименование=" + title +
                ", Количество=" + quantity + "}\n";
    }
}

class BasketImpl implements Basket {
    List<Product> products = new ArrayList<>();

    @Override
    public void addProduct(String product, int quantity) {
        products.add(new Product(product, quantity));
    }

    @Override
    public void removeProduct(String product) {
        products.removeIf(p -> p.title.equals(product));
    }

    @Override
    public void updateProductQuantity(String product, int quantity) {
        removeProduct(product);
        addProduct(product, quantity);
    }

    @Override
    public void clear() {
        products.clear();
    }

    @Override
    public List<String> getProducts() {
        return products.stream().map(Product::getTitle).toList();
    }

    @Override
    public int getProductQuantity(String product) {
        return getProductByTitle(product).getQuantity();
    }

    public Product getProductByTitle(String product) {
        return products
                .stream()
                .filter(p -> p.title.equals(product))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return "Корзина{" +
                "Продукты=" + products +
                '}';
    }
}

public class Task5 {
    public static void main(String[] args) {
        BasketImpl basket = new BasketImpl();
        basket.addProduct("Молоко", 10);
        basket.addProduct("Сок", 14);
        basket.addProduct("Йогурт", 8);
        System.out.println(basket);
        System.out.println(basket.getProducts());
        System.out.println(basket.getProductQuantity("Сок"));
        basket.updateProductQuantity("Сок", 4);
        System.out.println(basket);
        basket.removeProduct("Сок");
        System.out.println(basket);
        basket.addProduct("Сыр", 1);
        System.out.println(basket);
        basket.clear();
        System.out.println(basket);
    }
}
