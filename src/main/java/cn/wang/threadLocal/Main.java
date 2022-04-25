package cn.wang.threadLocal;

public class Main {
    public static void main(String[] args) {
        ThreadLocal<String> t1 = new ThreadLocal<>();
        t1.set("H1");
        t1.set("H2");
        //set方法  set(this,value),所以会被覆盖。。。
        System.out.println(t1.get());
        ThreadLocal<ThreadLocal> l1 = new ThreadLocal<>();
        ThreadLocal<String> s1 = new ThreadLocal<>();
        s1.set("H1");
        l1.set(s1);
        System.out.println(l1.get().get());
        //让子线程的ThreadLocal继承父线程的ThreadLocal
        //使用 inheritableThreadLocal
        InheritableThreadLocal<String> inheritTL = new InheritableThreadLocal<>();
        inheritTL.set("inheritable Thread Local");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + inheritTL.get());
            }
        });
        thread.setName("子进程");
        thread.start();


    }
}
