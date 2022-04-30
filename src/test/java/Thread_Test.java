import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class Thread_Test {
    @org.junit.Test
    public void test() throws InterruptedException {


        java.lang.Thread t1 = new java.lang.Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("AAA");
            }

        });
        java.lang.Thread t2 = new java.lang.Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("BBB");
            }
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        java.lang.Thread t3 = new java.lang.Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("CCC");
            }
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        //t1.join();//也可以顺序
        t2.start();
        t3.start();
    }

    @Test
    public void Thread_Null() throws InterruptedException {
        //线程能在运行的时候，被设置为null吗？
        Thread t1 = new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Is Living");
            }
        });
        t1.start();
        //当线程启动之后，即使再被设置为null也会继续执行
        t1 = null;

        TimeUnit.SECONDS.sleep(3);
        System.out.println("T1 == null");
    }
}
