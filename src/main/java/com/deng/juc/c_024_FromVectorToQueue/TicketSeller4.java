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
                    //当执行poll方法的时候，就会保证其他线程看到的队列元素少1
                    // Tips: 把String s变成全局的静态变量的时候，就会造成共享变量的问题。。。
                    String s = tickets.poll();
                    if (s == null) break;
                    else
                        System.out.println("sale -- "+s);
                }
            }).start();
        }
    }


}
