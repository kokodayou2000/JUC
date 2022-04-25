package cn.wang.deathLock;

public class Main {
    private static final Object resourceA = new Object();
    private static final Object resourceB = new Object();
    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA) {
                    System.out.println("线程：" + Thread.currentThread() + "获得到了资源 ResourceA");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread() + "正在等待获取资源 ResourceB");
                    synchronized (resourceB) {
                        System.out.println("线程："+Thread.currentThread() + "获取到了资源 ResourceB");
                    }
                }
            }
        });
        threadA.setName("线程A");
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB){
                    System.out.println("线程：" + Thread.currentThread()+"获取到了资源 ResourcedB");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程："+Thread.currentThread()+"正在获取资源 ResourcedA");
                    synchronized (resourceA){
                        System.out.println("线程："+Thread.currentThread()+"获取到了资源 ResourceB");
                    }
                }
            }
        });
        threadB.setName("线程B");
        threadA.start();
        threadB.start();
    }
}
