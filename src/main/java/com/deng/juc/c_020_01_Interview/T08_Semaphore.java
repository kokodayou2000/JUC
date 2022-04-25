package com.deng.juc.c_020_01_Interview;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/*
T1通过获取到信号量来保证原子性
在T1中调用T2打印"T2 end"
添加join保证t2结束后继续执行T1

 */
public class T08_Semaphore {

    volatile List list = new ArrayList();

    public void add(Object o) {
        list.add(o);
    }
    public int size() {
        return list.size();
    }
    static Thread t1 = null;
    static Thread t2 = null;

    public static void main(String[] args) {
        T08_Semaphore c = new T08_Semaphore();
        Semaphore s = new Semaphore(1);
        t1 = new Thread(()->{
            try {
                s.acquire();
                for (int i = 0; i < 5; i++) {
                    c.add(new Object());
                    System.out.println("add "+i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                t2.start();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    System.out.println(i);
                }
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T1");


        t2 = new Thread(() ->{
            try {
                s.acquire();
                System.out.println("t2 结束");
                s.release();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        },"t2");

        t1.start();

    }

}


