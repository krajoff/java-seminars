package Philosophes;

import java.util.concurrent.Semaphore;

/**
 * 1. Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
 * 2. Вилки лежат на столе между каждой парой ближайших философов.
 * 3. Каждый философ может либо есть, либо размышлять.
 * 4. Философ может есть только тогда, когда держит две вилки — взятую справа и слева.
 * 5. Философ не может есть два раза подряд, не прервавшись на размышления (можно не учитывать)
 * Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        int numbers = 5;

        // Создаем вилки
        Object[] forks = new Object[numbers];
        for (int i = 0; i < numbers; i++) {
            forks[i] = new Object();
        }

        for (int i = 0; i < numbers; i++) {
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % numbers];
            new Thread(new Philosopher(i, leftFork, rightFork)).start();
        }
    }
}
