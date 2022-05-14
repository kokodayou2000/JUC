package com.deng.juc.c_023_02_FromHashtableToCHM;

import java.util.HashMap;
import java.util.UUID;

public class T02_TestHashMap {
    /*
    无锁的效率更高，但是无法保证数据一致性
     */
    static HashMap<UUID,UUID> m = new HashMap<>();

    static int count = Constants.COUNT;
    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];
    static final int THREAD_COUNT = Constants.THREAD_COUNT;

    static {
        for (int i = 0 ; i < count; i++ ){
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count / THREAD_COUNT;

        public MyThread(int start){
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start+gap; i++){
                m.put(keys[i],values[i]);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
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
        System.out.println(end - start);
        System.out.println(m.size());
//-----------------------------
        start = System.currentTimeMillis();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                // foreach thread do 1千万次get方法
                for (int j = 0; j < 10000000; j++) {
                    m.get(keys[10]);
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }




}
