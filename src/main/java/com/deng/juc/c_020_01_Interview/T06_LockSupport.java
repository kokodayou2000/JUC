package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T06_LockSupport {
    volatile List<Object> list = new ArrayList<>();

    public void add(Object o){
        list.add(o);
    }

    public int size(){
        return list.size();
    }
    public static void main(String[] args) throws InterruptedException {
        T06_LockSupport t = new T06_LockSupport();
        Thread t2 = new Thread(() -> {
            if (t.list.size() != 5){
                LockSupport.park();
                System.out.println("unpark(t1)");
            }
        });
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.list.add(i);
                System.out.println(t.list.size());
                if (t.list.size() == 5){
                    //让当前线程终止
                    LockSupport.unpark(t2);
                }
            }
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        t1.start();
    }
}
