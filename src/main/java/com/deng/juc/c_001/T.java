package com.deng.juc.c_001;

public class T {
    private int count = 10;
    private final Object o = new Object();

    private void m(){
        synchronized (o) {
            count--;
            System.out.println(Thread.currentThread().getName()+" count = "+count);
        }
    }
}
