package Philosophes.seminar;

public class Seminar {

    public static void main(String[] args) {
       ex1();
    }
    public static void ex1(){
         Object objectA = new Object();
         Object objectB = new Object();

         Thread thread1 = new Thread(new Runnable() {
             @Override
             public void run() {
                 workThread1();
             }
             public void workThread1(){
                synchronized (objectA){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (objectB){
                        System.out.println("ObjectB");
                    }
                }
             }
         });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                workThread2();
            }
            public void workThread2(){
                synchronized (objectB){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (objectA){
                        System.out.println("ObjectB");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }

}
