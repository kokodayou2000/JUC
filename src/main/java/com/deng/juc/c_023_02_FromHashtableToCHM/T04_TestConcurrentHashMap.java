package com.deng.juc.c_023_02_FromHashtableToCHM;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class T04_TestConcurrentHashMap {

    //juc下的同步容器，使用了比较好的同步方法
    //这个容器的读有很好的效率提升，但是写的效率没有传统的带锁HashMap或者Hashtable高
    static Map<UUID,UUID> m = new ConcurrentHashMap<UUID, UUID>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread{
        int start ;
        int gap = count / THREAD_COUNT;
        MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap  ; i++) {
                m.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start  = System.currentTimeMillis();
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(i*(count/THREAD_COUNT));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("write speed ->"+(end - start));
        System.out.println(m.size());
        //---------------
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }

        for(Thread t : threads) {
            t.start();
        }

        for(Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end = System.currentTimeMillis();
        System.out.println("read speed ->"+(end - start));




    }

}
