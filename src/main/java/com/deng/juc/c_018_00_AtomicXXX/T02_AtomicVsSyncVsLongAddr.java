package com.deng.juc.c_018_00_AtomicXXX;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicVsSyncVsLongAddr {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    void m(){
        synchronized (this){
            for (int i = 0; i < 100000; i++) {
                count2++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    count1.incrementAndGet();
                };
            });
        }

        long start = System.currentTimeMillis();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Atomic   \t"+count1.get()+" "+(System.currentTimeMillis() - start));


        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                };
            });
        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("LongAddr \t"+count3+" "+(System.currentTimeMillis() - start));

        final Object lock = new Object();
        T02_AtomicVsSyncVsLongAddr t = new T02_AtomicVsSyncVsLongAddr();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    synchronized (lock){
                        count2++;
                    }
                }
            });
        }

        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("Sync     \t"+count2+" "+(System.currentTimeMillis() - start));

    }




}
