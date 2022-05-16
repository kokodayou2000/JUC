package com.deng.juc.c_026_01_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T05_ThreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 6; i++) {
            service.execute(()->{
                try{
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);

        service.shutdown();
        System.out.println("service is terminated : "+service.isTerminated());
        System.out.println("service is shutdown : "+service.isShutdown());
        System.out.println("service ->  "+service);

        TimeUnit.SECONDS.sleep(3);
        System.out.println("service is terminated : "+service.isTerminated());
        System.out.println("service is shutdown : "+service.isShutdown());
        System.out.println("service -> "+service);

    }
}
