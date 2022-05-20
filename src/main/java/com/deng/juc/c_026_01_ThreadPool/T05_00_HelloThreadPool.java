package com.deng.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class T05_00_HelloThreadPool {

    static class Task implements Runnable {
        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "Task " + i);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void main(String[] args) {
        //implement user's ThreadPoolExecutor
        //if corePool's Thread is full
        //insert into BlockingQueue,unit the queue is full
        //when if (maximumPoolSize > corePool) ,create a new Thread insert into pool
        //and run the task...
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 4,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(4),

                Executors.defaultThreadFactory(),
                //reject policy, if queue is full and pool also full ,when executor reject policy
//                new ThreadPoolExecutor.DiscardOldestPolicy());     //reject policy delete the alive longest thread in queue
//                new ThreadPoolExecutor.AbortPolicy());// throw exception...
//                new ThreadPoolExecutor.DiscardPolicy()); // discard don't throw exception
                new ThreadPoolExecutor.CallerRunsPolicy()); // caller thread executor task...

        for (int i = 0; i < 8; i++) {
            tpe.execute(new Task(i));
        }
        System.out.println(tpe.getQueue());
        tpe.execute(new Task(100));
        System.out.println(tpe.getQueue());
        tpe.shutdown();


    }
}
