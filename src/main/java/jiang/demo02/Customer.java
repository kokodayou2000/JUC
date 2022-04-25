package jiang.demo02;

import java.util.concurrent.TimeUnit;

public class Customer {
    //消费者

    private volatile int money = 100;

    static Store store;
    public Customer() {
        store = Store.getInstance();

    }
    public void start_Customer() throws InterruptedException {
        while (money>=0){
            synchronized (this){
                buyApple();
                buyBanana();
            }
        }
        System.out.println(Thread.currentThread()+"结束了消费");
    }

    private synchronized void buyApple() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        store.outStore("001",1);
        System.out.println(Thread.currentThread()+"消费了一个苹果");
        System.out.println("商店剩余"+store.map.get("001").capable+"个苹果");
        money--;
    }
    private synchronized void buyBanana() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        store.outStore("002",2);
        System.out.println(Thread.currentThread()+"消费了两个香蕉");
        System.out.println("商店剩余"+store.map.get("002").capable+"个香蕉");
        money-=2;
    }


}
