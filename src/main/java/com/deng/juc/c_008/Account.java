package com.deng.juc.c_008;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

//脏读问题
public class Account {
    static String name;
    static double balance;

    public synchronized void set(String name,double balance){
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()->{
            a.set("Tom",10.0);
//            System.out.println(Thread.currentThread().getName() + "  " + a.getBalance());
        }).start();
//        System.out.println(a.getBalance());
        int c = 0;
        //添加循环的次数可以发现a.getBalance的结果不同
        for (int i = 0; i < 1000; i++) {
            c++;
        }
        System.out.println(c);
        //这个输出 0.0是因为可能是main方法先执行到了a.getBalance()方法，所以直接就输出了
        System.out.println(Thread.currentThread().getName() + "  " + a.getBalance());


//        a.set("John",99.0);
        System.out.println(Thread.currentThread().getName() + "  " + a.getBalance());

    }

}

