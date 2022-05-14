package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class T04_00_BlockingQueue {
    static BlockingQueue<Character> q1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<Character> q2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {
        char[] aC = "abcde".toCharArray();
        char[] aI = "12345".toCharArray();
        new Thread(()->{
            for (char c : aI) {
                try {
                    q1.put(c);
                    System.out.println(q2.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (char c : aC) {
                try {
                    q2.put(c);
                    System.out.println(q1.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
