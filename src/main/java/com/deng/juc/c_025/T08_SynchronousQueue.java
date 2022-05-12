package com.deng.juc.c_025;

import java.util.concurrent.SynchronousQueue;

public class T08_SynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{

            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.put("aaa");

        System.out.println(strs.size());



    }
}
