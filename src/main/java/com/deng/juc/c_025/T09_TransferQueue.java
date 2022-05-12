package com.deng.juc.c_025;

import java.util.concurrent.LinkedTransferQueue;

public class T09_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<String>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strs.transfer("999");
    }
}
