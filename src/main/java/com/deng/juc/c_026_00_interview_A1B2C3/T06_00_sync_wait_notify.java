package com.deng.juc.c_026_00_interview_A1B2C3;

public class T06_00_sync_wait_notify {
    public static void main(String[] args) {
        char[] aI = "12345".toCharArray();
        char[] aC = "abced".toCharArray();
        Object lock = new Object();
        new Thread(()->{
            for (char c : aI) {
                synchronized (lock){
                    System.out.println(c);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        }).start();

        new Thread(()->{
            for (char c : aC) {
                synchronized (lock){
                    lock.notify();
                    System.out.println(c);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
