public class Test extends Thread {
    Test() {

    }

    Test(Runnable r) {
        super(r);
    }

    public void run() {
        System.out.println("1");
    }
}
