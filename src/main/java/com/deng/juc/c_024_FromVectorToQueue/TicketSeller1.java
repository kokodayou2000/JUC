package com.deng.juc.c_024_FromVectorToQueue;

import java.util.ArrayList;
import java.util.List;

public class TicketSeller1 {
    static List<String> tickets = new ArrayList<String>();
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add("ticket number:" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
              while(tickets.size() > 0){
                  System.out.println("seller --"+tickets.remove(0));
              }
            }).start();
        }
    }


}
