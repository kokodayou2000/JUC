package com.deng.juc.c_025;

import java.util.PriorityQueue;

public class T07_01_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> q = new PriorityQueue<>();

        q.add("c");
        q.add("d");
        q.add("b");
        q.add("a");
        q.add("f");
        q.add("e");

        for (int i = 0; i <6; i++) {
            System.out.print(q.poll()+"  ");
        }

    }
}
