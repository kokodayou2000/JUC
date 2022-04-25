package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class T07_LockSupport_WithoutSleep {
    volatile List<Object> list = new ArrayList<>();
    public void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    static Thread t1 ,t2;

    public static void main(String[] args) {
        T07_LockSupport_WithoutSleep t = new T07_LockSupport_WithoutSleep();

        t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.list.add(i);
                System.out.println(t.list.size());
                if (t.list.size() == 5){
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        });

        t2 = new Thread(()->{
            LockSupport.park();
            System.out.println("run t2");
            LockSupport.unpark(t1);
        });

        t1.start();
        t2.start();




    }

}
