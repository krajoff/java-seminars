public class Dog extends Animal{
    int Height;

    public Dog(String name, int age, int height) {
        super(name, age);
        Height = height;
    }

    @Override
    public void makeSound() {
        System.out.println("Гав-гав!");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "Height=" + Height +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
