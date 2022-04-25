package jiang.demo01;

import java.util.concurrent.Exchanger;

public class T2_exchange {
    static Exchanger<Integer> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            Integer x = 0;

            for (int i = 0; i < 50; i++) {
                try {
                        x = exchanger.exchange(x) + 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t1 ->" + x);
            }
        }).start();

        new Thread(()->{
            Integer x = 0;
            for (int i = 0; i < 50 ; i++) {
                try {
                        x = exchanger.exchange(x) + 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 ->"+x);
            }

        }).start();


    }


}
