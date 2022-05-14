package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

public class T05_00_AtomicInteger {
    static AtomicInteger AI = new AtomicInteger(1);
    public static void main(String[] args) {
        char[] aI = "12345".toCharArray();
        char[] aC = "abced".toCharArray();
        new Thread(()->{
            for (char c : aI) {
                while (AI.get() == 1){}
                System.out.println(c);
                AI.set(1);
            }
        }).start();
        new Thread(()->{
            for (char c : aC) {
                while (AI.get() == 2){};
                System.out.println(c);
                AI.set(2);
            }
        }).start();

    }
}
