package jiang.demo01;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class T6 {
    static class Station {
        //假设有100张票
        volatile AtomicInteger atomicInteger = new AtomicInteger(100);

        //提供一个取票的方法
        public synchronized int getTicket() {
            // -1并返回
            return atomicInteger.decrementAndGet();
        }

        public synchronized boolean isTicket() {
            //当没有票的时候返回false
            return atomicInteger.get() == 0;
        }
    }

    public static void main(String[] args) {
        Station s = new Station();
        Random random = new Random();

        new Thread(() -> {
            //模拟卖票
            while (true) {
                synchronized (T6.class) {
                    if (s.isTicket()) {
                        break;
                    }
                }
                {
                    try {
                        TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("当前余票数量" + s.getTicket());
            }
        }).start();

        new Thread(() -> {
            //模拟卖票
            while (s.isTicket()) {
                synchronized (T6.class) {
                    if (s.isTicket()) {
                        break;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前余票数量" + s.getTicket());
            }
        }).start();

        new Thread(() -> {
            //模拟卖票
            while (s.isTicket()) {
                synchronized (T6.class) {
                    if (s.isTicket()) {
                        break;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("当前余票数量" + s.getTicket());
            }

        }).start();


    }

}
