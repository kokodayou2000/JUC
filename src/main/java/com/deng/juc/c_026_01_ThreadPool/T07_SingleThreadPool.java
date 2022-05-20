package com.deng.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T07_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        //一个线程的线程池，为了保证线程是顺序执行的
        for (int i = 0; i < 5; i++) {
            final int j = i;
            service.execute(()->{
                //one thread in the pool
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }

    }
}
