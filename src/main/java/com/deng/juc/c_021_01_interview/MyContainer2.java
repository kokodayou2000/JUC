package com.deng.juc.c_021_01_interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2<T> {
    final private LinkedList<T> list = new LinkedList<>();
    final private int MAX = 10;
    private int count = 0;

    private final Lock lock = new ReentrantLock();
    private final Condition producer = lock.newCondition();
    private final Condition consumer = lock.newCondition();

    public void put(T t){
        try {
            //生产者
            lock.lock();
            while (list.size() == MAX) {
                producer.await();
            }
            list.add(t);
            ++count;
            consumer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public T get() {
        T t = null;
        try{
            //消费者
            lock.lock();
            while (list.size() == 0){
                consumer.await();
            }
            t = list.removeFirst();
            count--;
            producer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

        return t;
    }


    public static void main(String[] args) {

        MyContainer2<String> c = new MyContainer2<String>();
        /*
        十个消费者两个生产者
        先创建10给消费者线程，每个线程会进行5次消费
        消费：当没有可消费的时候，就会尝试唤醒生产者
        在2s后，会创建两个生产者线程
        每个生产者线程会进行25次生产
        生产：当生产达到最大值10的时候，就会唤醒消费者
         */



        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    c.put(Thread.currentThread().getName()+" "+j);
                }
            },"p"+i).start();
        }

    }


}
