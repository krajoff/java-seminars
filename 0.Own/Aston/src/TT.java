public class TT extends Thread{
    public static void main(String[] args) {
        TT t = new TT();
        t.start();
        System.out.println("one. ");
        t.start();
        System.out.println("two. ");
    }

    @Override
    public void run() {
        System.out.println("Thread ");
    }
}
