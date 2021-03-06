package com.deng.juc.c_025;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
    public static void main(String[] args) throws InterruptedException {
        Map<String,String> map = new ConcurrentHashMap<>();
        //ConcurrentSkipListMap其实是为了代替TreeMap的，因为CAS操作
        //在树结构上很难实现，所以使用了跳表来实现

//        Map<String,String> map = new ConcurrentSkipListMap<>();
//        Map<String,String> map = new Hashtable<>();
//        HashMap<String, String> map = new HashMap<>();

        Random r = new Random();
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        long start = System.currentTimeMillis();
        for(int i =0; i< threads.length;i++) {
            threads[i] = new Thread(() -> {
                //foreach thread execute insert into map key and value
                for (int j = 0; j < 10000; j++) {
                    map.put("a" + r.nextInt(100000), "a" + r.nextInt(10000));
                }
                latch.countDown();
            });
        }

        Arrays.asList(threads).forEach(Thread::start);

        latch.await();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(map.size());

    }
}
