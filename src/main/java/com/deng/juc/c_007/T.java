package com.deng.juc.c_007;

public class T {
    private synchronized void m1(){
        System.out.println("m1 start.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1 end..");
    }
    private void m2(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end..");
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m1,"t1").start();
        new Thread(t::m2,"t2").start();

    }

}
