package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T09_00_lock_condition {
    public static void main(String[] args) {
        char[] aI = "12345".toCharArray();
        char[] aC = "ABCDE".toCharArray();

        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        new Thread(()->{
            try{
                lock.lock();
                for (char c : aI) {
                    System.out.print(c);
                    c2.signal();
                    c1.await();
                }
                c2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }).start();

        new Thread(()->{
            try{
                lock.lock();
                for (char c : aC) {
                    System.out.print(c);
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }).start();

    }
}
