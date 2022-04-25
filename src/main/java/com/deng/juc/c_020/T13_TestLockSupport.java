package com.deng.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i==5){
                    LockSupport.park();
                }
                System.out.println("Thread "+i);
            }
        });
        t.start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //指定某个线程对象执行
            LockSupport.unpark(t);
        }
    }


}
