package deathLock;

import java.util.concurrent.locks.ReentrantLock;

public class T1 {
    static Object lock = new Object();
    static ReentrantLock reentrantLock = new ReentrantLock();
    public static void main(String[] args) {
        //使用一个线程创建死锁
        // 当一个线程拥有两把锁（A,B）的时候，在第二把(B)锁释放锁的时候，调用一个
        //中断，这个中断会尝试获取第一把锁(A)，这时候A会等待中断结束释放锁
        //中断会一直等待A锁被释放。

        //sychronized有锁重入的概念，所以并不好复现这个事件
        synchronized (lock){
        }


        reentrantLock.lock();
        interrupt();
        reentrantLock.unlock();
        System.out.println("end...");
    }
    public static void interrupt(){
        reentrantLock.lock();
    }

}
