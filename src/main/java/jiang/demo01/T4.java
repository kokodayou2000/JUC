
package jiang.demo01;

import java.util.concurrent.locks.LockSupport;

public class T4 {
    static Thread a,b,c = null;
    public static void main(String[] args) throws InterruptedException {
        //4、启动三个线程，线程ID分别设置成A、B、C,每个线程将自己的ID打印5遍，打印顺序为ABCABC....
        a = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (T4.class){
                    System.out.print(Thread.currentThread().getName());
                    LockSupport.park();
                    LockSupport.unpark(b);
                }
            }
        }, "A");

        b = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                    System.out.print(Thread.currentThread().getName());
                    LockSupport.park();
                    LockSupport.unpark(c);
            }
        }, "B");

        c = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName());
                LockSupport.unpark(a);
                LockSupport.park();
            }
        }, "C");


        a.start();
        b.start();
        c.start();
//        a.join();
//        b.join();
//        c.join();

    }

}
