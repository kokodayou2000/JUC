package com.deng.juc.c_022_RefTypeAndThreadlocal;

public class M {

    protected void finalize() throws Throwable{
        System.out.println("finalize");
    }
}
