package com.deng.juc.c_000;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
        testJoin();
    }
    static void testSleep() {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("A "+i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    static void testYield(){
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("---a--- "+i);
                if (i%10 == 0) Thread.yield(); //让当前线程从cpu离开（进入到等待队列中）
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                System.out.println("---b--- "+i);
                if (i%10 == 0) Thread.yield();
            }
        }).start();

    }
    static void testJoin(){
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A " + i);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join(); //让t1去执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("T2 Thread");
        });

        t1.start();
        t2.start();

    }


}
