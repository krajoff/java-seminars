public class Task27 implements Runnable{
    public void run(){
        System.out.println("1");
    }

    public static void main(String[] args) {
        new Thread(new Task27()).start();
    }
}
