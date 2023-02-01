import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int number = 5;
        int upperbound = 10;
        ex0(number, upperbound);
        ex1(number, upperbound);
        ex2(number, upperbound);
    }

    //  Пусть дан LinkedList с несколькими элементами. Реализуйте метод, который вернет “перевернутый” список.
    static void ex0(int number, int upperbound) {
        LinkedList<Integer> list = randomIntLinkedList(number, upperbound);
        System.out.println("Task 0 \nInitial list: " + list);
        LinkedList<Integer> reversedList = new LinkedList<>();
        ListIterator<Integer> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()) {
            reversedList.add(iterator.previous());
        }
        System.out.println("Reversed list: " + (reversedList));
    }

    //  Реализуйте очередь с помощью LinkedList со следующими методами: enqueue() - помещает элемент
    //  в конец очереди, dequeue() - возвращает первый элемент из очереди и удаляет его, first()
    //  - возвращает первый элемент из очереди, не удаляя.
    static void ex1(int number, int upperbound) {
        Deque<Integer> queue = new LinkedList<>();
        Random random = new Random();
        int element = 0;
        for (int i = 0; i < number; i++) {
            element = random.nextInt(upperbound);
            enqueue(queue, element);
        }
        enqueue(queue, 777);
        System.out.println("Task 1 \nQueue: " + queue);
        dequeue(queue);
        System.out.println("Queue of dequeue(): " + queue);
        first(queue);
    }

    //  Найдите сумму всех элементов LinkedList, сохраняя все элементы в списке. Используйте итератор
    static void ex2(int number, int upperbound) {
        LinkedList<Integer> list = randomIntLinkedList(number, upperbound);
        System.out.println("Task 2 \nThe list: " + list);
        int sum = 0;
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            sum += element;
        }
        System.out.println("Sum of list: " + sum);
    }

    static LinkedList<Integer> randomIntLinkedList(int number, int upperbound) {
        Random random = new Random();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            list.addFirst(random.nextInt(upperbound));
        }
        return list;
    }

    static void enqueue(Deque<Integer> queue, int element) {
        queue.offer(element);
    }

    static void dequeue(Deque<Integer> queue){
        System.out.println("Last element of queue: " + queue.getLast());
        queue.removeLast();
    }

    static void first(Deque<Integer> queue){
        System.out.println("First element of queue: " + queue.getFirst());
    }
}