public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(5, 1, 2, 3);
        list.print();
        list.remove(1);
        list.print();

        list.revert();
        list.print();


    }
}
