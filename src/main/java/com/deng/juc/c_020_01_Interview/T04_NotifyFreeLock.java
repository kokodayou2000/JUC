package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_NotifyFreeLock {
    List<Object> list = new ArrayList<>();
    public int size() {
        return list.size();
    }
    public void add(Object o){
        list.add(o);
    }

    public static void main(String[] args) throws InterruptedException {
        T04_NotifyFreeLock t = new T04_NotifyFreeLock();
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    t.list.add(i);
                    if (t.list.size() == 5){
                        //唤醒另外的线程,并让当前线程等待
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.notify();
                System.out.println("t2 running...");
            }
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t1.start();


    }




}
