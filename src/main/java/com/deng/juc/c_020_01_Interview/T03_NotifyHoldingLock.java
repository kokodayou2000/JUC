package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T03_NotifyHoldingLock {
   List<Object> list = new ArrayList<>();

   public void add(Object o) {
       list.add(o);
   }

   public int size() {
       return list.size();
   }

    public static void main(String[] args) throws InterruptedException {
        T03_NotifyHoldingLock c = new T03_NotifyHoldingLock();
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(i);
                    if (i == 5) {
                        lock.notifyAll();
//                        try {
//                            lock.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }

            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
//                System.out.println("size == 5");
//                lock.notify();
                while (true) {
                    if (c.size() != 5) {
                        try {
                            lock.wait();
                            System.out.println("run ...");
                            break;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t1.start();
    }

}
