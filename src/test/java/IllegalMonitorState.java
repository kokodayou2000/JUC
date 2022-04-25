import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.lang.Thread;

public class IllegalMonitorState {
    @Test
    public void T1() throws InterruptedException {
        Object o = new Object();
        o.wait();
    }

    @Test
    public void T2() throws InterruptedException {
        Object o = new Object();
        new Thread(()->{
            synchronized (o) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("Time stop 1s");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                o.notify();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("notify but also running ");
            }
        }).start();

        synchronized (o){
            System.out.println("Hello world!");
            o.wait();
            System.out.println("after notify .");
        }

    }


}
