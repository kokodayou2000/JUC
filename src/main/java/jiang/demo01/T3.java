package jiang.demo01;

import java.util.concurrent.TimeUnit;

public class T3 {
    public static void main(String[] args) {
        //3、写两个线程，一个线程打印1-52,另一个线程打印A-Z,打印顺序为12A34B56C.....5152Z
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                for (int i = 1; i <= 52; i+=2) {
                    System.out.print(i);
                    System.out.print(i+1);
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
                char ch = 'A';
                for (int i = 0; i < 26; i++) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(ch++);
                    lock.notify();
                }
                lock.notifyAll();
            }
        });

        t1.start();
        t2.start();
    }
}
