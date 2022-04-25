package com.deng.resume.demo01;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class MyFunction<T> {
    private volatile int size;
//    private volatile List<Integer> list = new ArrayList<>();
    volatile static List list = Collections.synchronizedList(new LinkedList<>());

    private MyFunction(){

    }

    public synchronized void add(int value){
        list.add(value);
        size = getSize();
    }

    public synchronized int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        MyFunction<Object> function = new MyFunction<>();
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                function.add(i);
                System.out.println("getSize()"+function.getSize());
            }
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end ?");
        });
        t1.start();


        new Thread(()->{
            System.out.println("cons");
            while (true){
                if (function.size == 5){
                    System.out.println("Park");
                    LockSupport.park(t1);
                }
            }

        }).start();

    }


}
