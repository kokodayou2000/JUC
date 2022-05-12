package com.deng.juc.c_024_FromVectorToQueue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TicketSeller4 {
    static Queue<String> tickets = new ConcurrentLinkedDeque<String>();

    static {
        for (int i = 0; i < 1000; i++) {
            tickets.add("tickets num:"+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    String s = tickets.poll();
                    if (s == null) break;
                    else
                        System.out.println("sale -- "+s);
                }
            }).start();
        }
    }


}
