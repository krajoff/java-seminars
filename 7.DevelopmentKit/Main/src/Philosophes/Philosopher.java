package Philosophes;

public class Philosopher implements Runnable {
    private int id;
    protected int countEating;
    private Object leftFork;
    private Object rightFork;
    Boolean justEaten;

    public Philosopher(int id, Object leftFork, Object rightFork) {
        this.id = id;
        this.countEating = 0;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        justEaten = false;
    }

    public void eat() {
        countEating++;
        justEaten = true;
        System.out.println("Philosopher " + (id + 1) + " is eating (" + countEating + " times)");
    }


    public void speculate() {
        System.out.println("Philosopher " + (id + 1) + " is speculating...");
        justEaten = false;
    }

    @Override
    public void run() {
        while (countEating < 3) {
            speculate();
            if (!justEaten) {
                eat();
            }
        }
    }
}
