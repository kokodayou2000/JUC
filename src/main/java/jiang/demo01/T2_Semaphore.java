package jiang.demo01;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class T2_Semaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        new Thread(()->{
            for (int i = 0; i <=100; i+=2) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1 -> "+i);
                semaphore.release();
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        new Thread(()->{
            for (int i = 1; i < 100; i+=2) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T2 -> "+i);
                semaphore.release();
                try {
                    TimeUnit.MILLISECONDS.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
