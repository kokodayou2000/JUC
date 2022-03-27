

public class Thread {
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
}
