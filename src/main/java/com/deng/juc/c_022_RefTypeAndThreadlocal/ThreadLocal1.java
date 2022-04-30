package com.deng.juc.c_022_RefTypeAndThreadlocal;

import java.util.concurrent.TimeUnit;

public class ThreadLocal1 {
    volatile static Person p = new Person();

    public static void main(String[] args) {
        //  线程2修改了共享变量
        //
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(p.name);

        }).start();
        new Thread(()->{

            p.name = "Jerk";
        }).start();

    }

}

class Person{
    String name = "Tom";
}