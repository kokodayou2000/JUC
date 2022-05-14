package com.deng.juc.c_026_00_interview_A1B2C3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T08_00_lock_condition {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        char[] aI = "12345".toCharArray();
        char[] aC = "ABCDE".toCharArray();

        new Thread(()->{
            try {
            lock.lock();
            for (char c : aI) {
                System.out.print(c);
                condition.signal();
                condition.await();
                }
                //在输出5或者E的时候，某一个线程必然会处于等待状态
                //这时就需要signal唤醒了，等到这个线程unlock的时候，另外的线程还能获取到lock
                condition.signal();
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
                    condition.signal();
                    condition.await();
                }

                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }

        }).start();
    }
}
