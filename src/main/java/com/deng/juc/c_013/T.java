package com.deng.juc.c_013;

import java.util.ArrayList;
import java.util.List;

public class T {
//    volatile
    volatile int count = 0;
    void m(){
        for (int i = 0; i < 10000; i++) {
            //count++并不是原子性的操作可能在这个步骤发生一些多线程的问题
            synchronized (this){
            count++;
            }
        }
    }

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        T t = new T();
        for (int i = 0; i < 10; i++) {
            list.add(new Thread(t::m,"t"+i));
        }
        list.forEach((o)->o.start());
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(t.count);

    }

}
