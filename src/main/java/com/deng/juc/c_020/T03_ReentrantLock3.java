package com.deng.juc.c_020;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock3 {
    ReentrantLock lock = new ReentrantLock();
    void m1(){
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName());
            m2();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("m1");
    }
    void m2(){
        boolean locked = true;
        try {
            locked = lock.tryLock(2,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread().getName()+"  -> "+locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("m2...");
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 t2 = new T03_ReentrantLock3();
        new Thread(t2::m1).start();


    }



}
