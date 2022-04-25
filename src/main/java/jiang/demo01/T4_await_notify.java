package jiang.demo01;

import java.util.concurrent.atomic.AtomicInteger;

public class T4_await_notify {

    int flag = 0;
    public synchronized void printA() throws InterruptedException {
        for (int i = 1; i <= 5 ; i++) {
            while (flag != 0){
                this.wait();
            }
            System.out.print(Thread.currentThread().getName());
            flag = 1;
            this.notifyAll();
        }
    }

    public synchronized void printB() throws InterruptedException {
        for (int i = 1; i <= 5 ; i++) {
            while (flag != 1){
                this.wait();
            }
            System.out.print(Thread.currentThread().getName());
            flag = 2;
            this.notifyAll();
        }
    }

    public synchronized void printC() throws InterruptedException {
        for (int i = 1; i <= 5 ; i++) {
            while (flag != 2){
                this.wait();
            }
            System.out.print(Thread.currentThread().getName());
            flag = 0;
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        T4_await_notify t = new T4_await_notify();

        new Thread(() -> {
            try {
                t.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(() -> {
            try {
                t.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(() -> {
            try {
                t.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();


    }
}
