package com.deng.juc.c_022_RefTypeAndThreadlocal;

import java.lang.ref.SoftReference;

public class T02_SoftReference {
    public static void main(String[] args) {
        //软引用主要是用来做缓存的，比如去读取一个大的图片，虽然可以

        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        //先设置堆内存大小 vm option    -Xms20M -Xmx20M
        System.out.println(m.get());
        //调用了gc，但是jvm还有空间，就不会清理掉软引用
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        //再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次
        //如果不够，会把软引用干掉
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }
}
