package com.deng.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        //每一个线程有单独的队列，当某个线程将它维护的队列执行完成的时候，就会去别的线程维护的队列里面去“偷任务”
        //从tail偷，增加到tail上
        //当对线程自己维护的队列进行push or pop 的时候，不需要加锁，但是执行pop work steal需要加锁
        ExecutorService service = Executors.newWorkStealingPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        System.in.read();

    }

    static class R implements Runnable {
        int time;

        R(int t){
            this.time = t;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + " " + Thread.currentThread().getName());
        }
    }
}
