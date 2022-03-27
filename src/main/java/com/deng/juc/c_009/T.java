package com.deng.juc.c_009;

import java.util.concurrent.TimeUnit;

public class T {
    synchronized void m1(){
        System.out.println("m1 start ");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m2();
        System.out.println("m1 end  ");
    }
    void m2(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1).start();
    }
}
