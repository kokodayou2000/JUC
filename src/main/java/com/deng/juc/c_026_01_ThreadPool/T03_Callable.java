package com.deng.juc.c_026_01_ThreadPool;

import java.util.concurrent.*;

public class T03_Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable callable = () -> "Hello Callable";

        Runnable t = ()->{
            System.out.println("???");
        };
        new Thread(t).start();

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(callable);

        System.out.println(future.get());

        service.shutdown();

    }
}
