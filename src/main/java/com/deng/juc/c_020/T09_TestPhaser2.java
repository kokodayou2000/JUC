package com.deng.juc.c_020;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class T09_TestPhaser2 {
    static Random r = new Random();
    static MarriagePhaser phaser = new MarriagePhaser();

    public static void main(String[] args) {
        phaser.bulkRegister(7);
        for (int i = 0; i < 5; i++) {
            new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();


    }



    static class MarriagePhaser extends Phaser{
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("all people arrived ... "+registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("all people eta ... "+registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("all people leave ... "+registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("marry end. ... "+registeredParties);
                    System.out.println();
                    return false;
                default:
                    return true;
            }
        }
    }

    private static void milliSleep(int milli){
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Person implements Runnable {
        String name;

        public Person(String name) {
            this.name = name;
        }
        Random r = new Random();

        public void arrive() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 到达现场\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void eat() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 吃完\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void leave() {
            milliSleep(r.nextInt(1000));
            System.out.printf("%s 离开\n",name);
            phaser.arriveAndAwaitAdvance();
        }
        public void hug() {
            if (name.equals("新郎")|| name.equals("新娘")){
                milliSleep(r.nextInt(1000));
                System.out.printf("%s 洞房\n",name);
                phaser.arriveAndAwaitAdvance();
            }else{
                phaser.arriveAndDeregister();
            }
        }
        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
