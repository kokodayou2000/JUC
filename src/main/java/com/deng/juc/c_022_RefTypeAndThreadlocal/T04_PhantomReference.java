package com.deng.juc.c_022_RefTypeAndThreadlocal;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

public class T04_PhantomReference {

    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        //虚引用是为了管理堆外内存的，一般程序员不会使用这个对象，通常是写虚拟机的人使用
        //场景：NIO netty之类的需要操作堆外内存，（堆外内存）就是操作系统管理的内存的时候
        //当DirectByteBuffer指向一个堆外内存，如果这个DirectByteBuffer变成null,指向中断了
        //这时jvm是无法管理那一片堆外内存的
        //如果使用虚引用的话，当检查queue出现对象的时候，说明DirectByteBuffer被gc的时候，做出对堆外内存的一些处理即可(free or delete or unsafe类)

        // -Xms20M -Xmx20M
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(()->{
            while (true){
                //每秒添加1M的堆外内存空间
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //你无法获取虚引用里面的值new PhantomReference(new M(),QUEUE);那个new M()无法获取到
                System.out.println(phantomReference.get());
            }
        }).start();
        new Thread(()->{
            while (true) {
                //当触发垃圾回收的时候，虚引用会在这个队列里填充一个值
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("---虚拟引用对象被jvm回收了---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
