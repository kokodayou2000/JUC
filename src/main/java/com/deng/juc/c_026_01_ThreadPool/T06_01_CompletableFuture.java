package com.deng.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T06_01_CompletableFuture {
    public static void main(String[] args) {
        long start ,end ;

        start = System.currentTimeMillis();

        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfJD);

        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();

        CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTM)
                .thenApply(String::valueOf)
                .thenApply(str->"price " +str)
                .thenAccept(System.out::println);
        end = System.currentTimeMillis();
        System.out.println("use completable future! "+(end - start));

        try{
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static double priceOfTM(){
        delay("TM");
        return 1.00;
    }
    private static double priceOfTB() {
        delay("TB");
        return 2.00;
    }
    private static double priceOfJD() {
        delay("JD");
        return 3.00;
    }

    private static void delay(String str) {
        int time = new Random().nextInt(500);
        try{
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Function=%s After %s sleep!\n",str,time);
    }
}
