package com.deng.juc.c_025;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> lists = new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        lists.add("a"+r.nextInt(10000));
                    }
                }
            };
            threads[i] = new Thread(task);
        }
        runAndComputeTime(threads);
        System.out.println(lists.size());
    }

    static void runAndComputeTime(Thread[] threads){
        long start = System.currentTimeMillis();
        Arrays.stream(threads).forEach(Thread::start);
        Arrays.stream(threads).forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(System.currentTimeMillis() - start);

    }
}
