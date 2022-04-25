package jiang.demo02;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Producer producer = new Producer();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producer.start_production();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        Thread C1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Customer().start_Customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread C2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Customer().start_Customer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        C1.setName("C1");
        C2.setName("C2");
        C1.start();
        C2.start();



    }
}
