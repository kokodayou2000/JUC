package com.deng.juc.c_025;

import java.util.Iterator;
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
            //当执行q.poll()的时候，容器的大小会变，所以判断条件不能是q.size();
//            System.out.print(q.poll()+"  ");
        }
        Iterator<String> iterator = q.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
