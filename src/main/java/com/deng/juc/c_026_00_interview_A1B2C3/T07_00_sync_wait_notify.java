package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.TimeUnit;

public class T07_00_sync_wait_notify {

    private static volatile boolean t2Started = false;

    public static void main(String[] args) throws InterruptedException {
        final Object lock = new Object();

        char[] aI = "12345".toCharArray();
        char[] aC = "ABCDE".toCharArray();

        new Thread(()->{
            synchronized (lock) {
                while (!t2Started) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : aI) {
                    System.out.println(c);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        },"t1").start();

//        TimeUnit.MILLISECONDS.sleep(100);
        new Thread(()->{
            synchronized (lock) {
                for (char c : aC) {
                    System.out.println(c);
                    t2Started = true;

                    lock.notify();
                    try {

                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        },"t2").start();
    }
}