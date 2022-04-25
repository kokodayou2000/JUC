package jiang.demo01;

import java.util.concurrent.TimeUnit;

public class T1 {
    public static void main(String[] args) throws InterruptedException {
        //1、有a、b两个线程，要求a执行完才开始执行b,b执行完再开始执行a
        Thread t1 = new Thread(() -> {
            System.out.println("t1 start...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end...");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("t2 start...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2 end...");
        });


        t1.start();
        t1.join();
        t2.join();
        t2.start();

    }

}
