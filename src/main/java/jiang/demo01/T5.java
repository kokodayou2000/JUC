package jiang.demo01;

public class T5 {

    static int t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    static Thread tt1,tt2,tt3,tt4,tt5,tt6,tt7,tt8,tt9,tt10 =null;
    public static void main(String[] args) throws InterruptedException {
        //5、编写10个线程，第一个线程从1加到10,
        //第二个从2加到20....第十个从91加到100,最后把10个线程的结果想加
        //需要共享变量才能实现值的共享
        //保证同步吧


        tt1 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 1; i <= 10; i++) {
                    T5.t1+=i;
                }
            }
        });
        tt2 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 11; i <= 20; i++) {
                    T5.t2+=i;
                }
            }
        });
        tt3 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 21; i <= 30; i++) {
                    T5.t3+=i;
                }
            }
        });
        tt4 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 31; i <= 40; i++) {
                    T5.t4+=i;
                }
            }
        });
        tt5 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 41; i <= 50; i++) {
                    T5.t5+=i;
                }
            }
        });
        tt6 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 51; i <= 60; i++) {
                    T5.t6+=i;
                }
            }
        });
        tt7 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 61; i <= 70; i++) {
                    T5.t7+=i;
                }
            }
        });
        tt8 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 71; i <= 80; i++) {
                    T5.t8+=i;
                }
            }
        });
        tt9 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 81; i <= 90; i++) {
                    T5.t9+=i;
                }
            }
        });
        tt10 = new Thread(() -> {
            synchronized (T5.class){
                for (int i = 91; i <= 100; i++) {
                    T5.t10+=i;
                }
            }
        });

        tt1.start();
        tt2.start();
        tt3.start();
        tt4.start();
        tt5.start();
        tt6.start();
        tt7.start();
        tt8.start();
        tt9.start();
        tt10.start();


        tt1.join();
        tt2.join();
        tt3.join();
        tt4.join();
        tt5.join();
        tt6.join();
        tt7.join();
        tt8.join();
        tt9.join();
        tt10.join();

        long sum = t1+t2+t3+t4+t5+t6+t7+t8+t9+t10;
        System.out.println(sum);




    }
}
