package com.deng.juc.c_022_RefTypeAndThreadlocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal2 {
    static ThreadLocal<Person1> tl = new ThreadLocal<>();



    public static void main(String[] args) {

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get().name);
        }).start();

        new Thread(()->{
            tl.set(new Person1());
            System.out.println("T2 Name = "+tl.get().name);
        }).start();
    }

}

class Person1{
    String name = "Tom";
}
