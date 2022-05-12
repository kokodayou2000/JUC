package com.deng.juc.c_022_RefTypeAndThreadlocal;

import java.lang.ref.WeakReference;

public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        //弱引用只存活到下一次垃圾回收为止
        System.out.println(m.get());

        //当一个强引用指向一个弱引用的时候，当强引用消失掉的时候，弱引用自动消失
        //一般用在容器里使用。
        // WeakHashMap

        //不同的threadLocal tl1 tl2 tl3维护一个map
        ThreadLocal<M> tl = new ThreadLocal<>();
        //getMap(t)是获取到当前线程的map
        //map.set(this,value)这里的this是tl .
        tl.set(new M());
        //threadLocal使用了弱引用的(entry)键值对，然后tl指向这个entry
        //当tl消失的时候，因为entry是弱引用的，使用entry会自动被gc
        //但是如果entry的key是null的时候，也就是说entry的value将无法被访问到
        //这个map也会一直的累积，导致内存泄漏 memory leak
        tl.remove();//最好使用remove方法解决这个问题
    }
}
