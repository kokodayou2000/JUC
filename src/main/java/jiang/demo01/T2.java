package jiang.demo01;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T2 {
    public static void main(String[] args) {
        Object lock = new Object();
//        ReentrantLock lock = new ReentrantLock();
        //2、两个线程轮流打印数字，1到100
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                for (int i = 0; i <= 100; i += 2) {
                    System.out.println("T1->" + i);
                    lock.notify();

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notifyAll();
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i < 100; i+=2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("T2->" + i);
                    lock.notify();
                }
                lock.notifyAll();
            }
        });

        t1.start();
        t2.start();


    }
}
