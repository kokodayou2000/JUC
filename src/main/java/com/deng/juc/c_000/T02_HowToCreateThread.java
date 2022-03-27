package com.deng.juc.c_000;

public class T02_HowToCreateThread {
    static class MyThread extends Thread{
        public MyThread() {
            System.out.println(Thread.currentThread().getName());
        }
    }
    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        //direct start
        new MyThread().start();
        //in the Thread then start
        new Thread(new MyRunnable()).start();
        //anonymous class
        new Thread(()->{
            System.out.println(Thread.currentThread().getName());
        }).start();

    }
}
