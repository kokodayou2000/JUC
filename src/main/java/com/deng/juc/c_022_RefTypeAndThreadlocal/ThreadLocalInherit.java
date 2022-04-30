package com.deng.juc.c_022_RefTypeAndThreadlocal;

public class ThreadLocalInherit {
    //继承ThreadLocal
    static InheritableThreadLocal<Person2> itl = new InheritableThreadLocal<Person2>();
    static ThreadLocal<Person2> tl = new ThreadLocal<Person2>();

    public static void main(String[] args) {
        /*
        ThreadLocal类的功能主要是为了实现共享变量的线程独有
         */
        itl.set(new Person2());
        tl.set(new Person2());
        new Thread(()->{

            //子进程并不能使用父进程创建的ThreadLocal
            System.out.println(tl.get().name);

            //所以就出现了InheritThreadLocal类
            System.out.println(itl.get().name);

        }).start();
        new Thread(()->{

            System.out.println(itl.get().name);

        }).start();
    }
}
class Person2{
    String name = "Tom";
}
