package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.atomic.AtomicInteger;

public class T03_00_cas {
    enum ReadyToRun{T1,T2};
    static volatile ReadyToRun r = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] aC = "abcdef".toCharArray();
        char[] aI = "12345".toCharArray();

        new Thread(()->{
            for (char c : aC) {
                while (r == ReadyToRun.T1){}
                System.out.println(c);
                r=ReadyToRun.T1;
            }
        }).start();

        new Thread(()->{
            for (char c : aI) {
                while (r == ReadyToRun.T2){}
                System.out.println(c);
                r = ReadyToRun.T2;
            }
        }).start();
    }
}
