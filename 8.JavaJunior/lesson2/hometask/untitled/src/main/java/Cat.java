public class Cat extends Animal {
    int Weight;

    public Cat(String name, int age, int weight) {
        super(name, age);
        Weight = weight;
    }

    @Override
    public void makeSound() {
        System.out.println(name + ": Мяу!");
    }
}
