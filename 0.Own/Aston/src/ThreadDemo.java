public class ThreadDemo {
    public static void main(String[] args) {
        new Test().start();
        new Test(new RunnableDemo()).start();
    }
}
