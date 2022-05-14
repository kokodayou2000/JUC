package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class T02_00_LockSupport {

    static Thread t1 = null;
    static Thread t2 = null;
    public static void main(String[] args) {
        char[] aI = "123456".toCharArray();
        char[] aC = "abcdef".toCharArray();
        t1 = new Thread(()->{
            for (char c : aI) {
                System.out.print(c);
                LockSupport.park();
                LockSupport.unpark(t2);
            }
        });
        t2 = new Thread(()->{
            for (char c : aC) {
                System.out.print(c);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();


    }
}
