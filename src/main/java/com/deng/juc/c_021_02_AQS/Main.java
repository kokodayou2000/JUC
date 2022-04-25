package com.deng.juc.c_021_02_AQS;

import java.util.concurrent.locks.Lock;

public class Main {
    public static int m = 0;
    public static Lock lock = new MyLock();

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[300];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{

                try{
                    lock.lock();
                    for (int j = 0; j < 100; j++) {
                        m++;
                    }
                }finally {
                    lock.unlock();
                }

            });
            for (Thread thread : threads) {
                thread.start();
            }
            for (Thread thread : threads) {
                thread.join();
            }

            System.out.println(m);

        }

    }


}
