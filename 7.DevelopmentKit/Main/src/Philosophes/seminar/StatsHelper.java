package Philosophes.seminar;

import java.util.Scanner;

public class StatsHelper {
    private static int cnt = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread readThread = new Thread(() -> {
            Scanner in = new Scanner(System.in);
            while (in.hasNext()) {
                in.nextLine();
                cnt++;
            }
        });
        readThread.setDaemon(true);
        readThread.start();
        while (true) {
            System.out.println(cnt + " message inputted by user");
            Thread.sleep(1000);
        }
    }
}
