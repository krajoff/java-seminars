public class Laptop {
    String model;
    String company;
    int memoryPrimary;
    String color;
    String systemName;
    int memoryHDD;
    double displaySize;


    String getModel() {
        return model;
    }

    public String toString() {
        return String.format("%s %s: %.1f\", %dМб ОЗУ, диск %dГб, цвет %s, %s." +
                "", company, model, displaySize, memoryPrimary, memoryHDD, color, systemName);
    }
}
