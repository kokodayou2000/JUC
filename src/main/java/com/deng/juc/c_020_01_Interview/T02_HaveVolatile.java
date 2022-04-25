package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;

public class T02_HaveVolatile {
    volatile List<Object> list = new ArrayList<>();

    public void add(Object o) {
        list.add(o);
    }
    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        T02_HaveVolatile t01 = new T02_HaveVolatile();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t01.list.add(i);
                System.out.println(t01.getSize());
            }
        });

        Thread t2 = new Thread(()->{
          while (true){
              if (t01.getSize() == 5){
                  System.out.println("park");
                  break;
              }
          }
        });
        t1.start();
        t2.start();
    }

}
