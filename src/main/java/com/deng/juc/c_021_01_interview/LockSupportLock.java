package com.deng.juc.c_021_01_interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

public class LockSupportLock {
    List list = new ArrayList();
    public void add(Object o) {
        list.add(o);
    }
    public int size() {
        return list.size();
    }

    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) {

        LockSupportLock s = new LockSupportLock();
        t1 = new Thread(()->{
            LockSupport.park();
            System.out.println("T1 Run");
            LockSupport.unpark(t2);

        },"T1");
        t1.start();
        t2 = new Thread(()->{
            for (int i = 0; i < 10; i++) {
                if (i == 5){
                    LockSupport.unpark(t1);
                    LockSupport.park();
                }
                s.add(new Object());
                System.out.println(i);
            }

        },"T2");

        t2.start();


    }


}
