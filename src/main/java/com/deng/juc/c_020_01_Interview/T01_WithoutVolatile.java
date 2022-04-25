package com.deng.juc.c_020_01_Interview;

import java.util.ArrayList;
import java.util.List;

public class T01_WithoutVolatile {
    List<Object> list = new ArrayList<>();
    public void add(Object o) {
        list.add(o);
    }
    public int getSize(){
        return list.size();
    }

    public static void main(String[] args) {
        T01_WithoutVolatile t01 = new T01_WithoutVolatile();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t01.list.add(i);
                System.out.println(t01.getSize());
            }
        });

        Thread t2 = new Thread(()->{
          while (true){
              if (t01.list.size() == 5){
                  System.out.println("park");
                  break;
              }
          }
        });
        t1.start();
        t2.start();
    }

}
