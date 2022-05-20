package com.deng.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T06_00_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(3000);
            return 1000;
        });

        new Thread(task).start(); //use Thread execute,The task is running now.
        TimeUnit.SECONDS.sleep(3);
        System.out.println(task.get());//async

        ExecutorService service = Executors.newFixedThreadPool(5);
        Integer res = 1;
        Future<Integer> f = service.submit(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        });
        System.out.println("now already submit, callable function running... " +
                "but main is also running. " +
                " this is async.");

        TimeUnit.SECONDS.sleep(5);
        System.out.println("main wait end");
        //don't need to wait again 5s
        System.out.println(f.get());
        System.out.println("callable wait end");
        System.out.println(f.isDone());

    }
}
