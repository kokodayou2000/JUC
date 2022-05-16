package com.deng.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;


public class T14_MyRejectedHandler {
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4, 4,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                Executors.defaultThreadFactory(),
                new MyHandler()
        );
        System.out.println(tpe);
    }

    static class MyHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            if (executor.getQueue().size() < 1000){

            }
        }
    }
}
