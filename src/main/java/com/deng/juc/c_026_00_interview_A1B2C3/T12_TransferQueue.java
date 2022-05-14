package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class T12_TransferQueue {
    public static void main(String[] args) {
        char[] aI = "12345".toCharArray();
        char[] aC = "ABCDE".toCharArray();
        TransferQueue<Character> queue = new LinkedTransferQueue<>();

        new Thread(()->{
            try{
                for (char c : aI) {
                    System.out.print(queue.take());
                    queue.transfer(c);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try{
                for (char c : aC) {
                    queue.transfer(c);
                    System.out.print(queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();



    }
}
