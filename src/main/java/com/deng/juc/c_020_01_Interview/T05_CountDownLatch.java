package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T05_CountDownLatch {
    static CountDownLatch c = new CountDownLatch(1);
    volatile List<Object> list = new ArrayList<>();
    public void add(Object o){
        list.add(o);
    }
    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T05_CountDownLatch t = new T05_CountDownLatch();
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.list.add(i);
                System.out.println(i);
                if (t.list.size() == 5) {
                    c.countDown();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("T2 running");
            if (t.list.size() != 5) {
                try {
                    c.await();
                    System.out.println("T2 ending");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t1.start();

    }



}
