package jyy.cs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class main {
    private static int count = 0;

    public static void main(String[] args) {
        //生产者消费者吧
        int n = 3;
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i < 10; i++) {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true) {
                        while (lock.tryLock()) {
                            if (count == n) {
                                //说明已经达到了阈值，释放锁吧
                                lock.unlock();
                                break;
                            }
                            //获取锁成功，打印 (
                            count++;
                            System.out.print("(");
                            lock.unlock();
                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        while (lock.tryLock()) {
                            //尝试获取锁，如果当n == 0，说明已经空了
                            if (count == 0) {
                                lock.unlock();
                                break;
                            }
                            count--;
                            System.out.print(")");
                            lock.unlock();
                            try {
                                TimeUnit.MILLISECONDS.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }
}
