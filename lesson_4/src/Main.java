import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ex0(10, 100);
    }

    //  Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.
    static void ex0(int number, int upperbound) {
        LinkedList list = randomIntLinkedList(number, upperbound);
        System.out.println("Initial list: " + list);
        LinkedList<Integer> reversedlist = new LinkedList();
//        for (int i = number; i < number; i++) {
//            reversedlist
//        }
        System.out.println("Reversed list: " + (reversedlist));
    }

    //  Реализуйте очередь с помощью LinkedList со следующими методами: enqueue() - помещает элемент
//  в конец очереди, dequeue() - возвращает первый элемент из очереди и удаляет его, first()
//  - возвращает первый элемент из очереди, не удаляя.
    static void ex1() {

    }

    //  Найдите сумму всех элементов LinkedList, сохраняя все элементы в списке. Используйте итератор
    static void ex2() {

    }

    static LinkedList randomIntLinkedList(int number, int upperbound) {
        Random random = new Random();
        LinkedList<Integer> list = new LinkedList();
        for (int i = 0; i < number; i++) {
            list.add(random.nextInt(upperbound));
        }
        return list;
    }
}