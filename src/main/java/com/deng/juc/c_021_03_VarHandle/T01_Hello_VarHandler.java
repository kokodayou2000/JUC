package com.deng.juc.c_021_03_VarHandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.logging.Handler;

public class T01_Hello_VarHandler {

    private int x;

    private static VarHandle handle ;

    static{
        try {
            handle = MethodHandles.lookup().findVarHandle(T01_Hello_VarHandler.class,"x",int.class);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        T01_Hello_VarHandler t = new T01_Hello_VarHandler();

//        System.out.println(t.x);
        System.out.println("handle.get(t)  "+handle.get(t));
        handle.set(t,10);

        System.out.println("handle.get(t)  "+handle.get(t));
        //cas
        System.out.println("cas 10 11  "+handle.compareAndSet(t, 10, 11));
        System.out.println("handle.get(t)  "+handle.get(t));
        System.out.println("cas 10 20  "+handle.compareAndSet(t, 10, 20));
        System.out.println("handle.get(t)  "+handle.get(t));


    }
}
