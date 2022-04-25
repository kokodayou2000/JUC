package com.deng.juc.c_020;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class T07_CyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, ()->{
            System.out.println("run barrier");
        });

        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
}
    }
}
