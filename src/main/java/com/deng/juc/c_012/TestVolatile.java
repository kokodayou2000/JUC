package com.deng.juc.c_012;

import java.util.concurrent.TimeUnit;

public class TestVolatile {
    volatile boolean running = true;
    private void m(){
        System.out.println("m1 start");
        while (running){

        }
        System.out.println("m1 end");
    }

    public static void main(String[] args) {
        TestVolatile testVolatile = new TestVolatile();
        new Thread(testVolatile::m,"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        testVolatile.running = false;



    }


}
