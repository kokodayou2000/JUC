package com.deng.juc.c_000;

public class T04_ThreadState {
    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(this.getState());
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        System.out.println(t.getState());
        t.start();

        t.join();

        System.out.println(t.getState());

    }

}
