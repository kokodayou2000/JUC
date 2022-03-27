package com.deng.juc.c_000;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class T01_WhatIsThread {
    //静态类已经加载到了内存中，使用可以直接使用T1的公共构造方法
    private static class T1 extends Thread{


        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
//        new T1().start();

        new T1().start();
        for (int i = 0; i < 10; i++) {
            TimeUnit.MICROSECONDS.sleep(1);
            System.out.println("Main");
        }


    }
}
