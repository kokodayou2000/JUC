package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class T04_01_BlockQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);
        ArrayBlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);
        char[] aI = "12345".toCharArray();
        char[] aC = "ABCDE".toCharArray();
        new Thread(()->{
            for (char c : aI) {
                System.out.print(c);
                try {
                    q2.take();
                    q1.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(()->{
            for (char c : aC) {
                System.out.print(c);
                try {
                    q2.put("ok");
                    q1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
