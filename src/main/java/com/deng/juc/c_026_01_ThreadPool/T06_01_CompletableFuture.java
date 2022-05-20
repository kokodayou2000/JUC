package com.deng.juc.c_026_01_ThreadPool;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T06_01_CompletableFuture {
    public static void main(String[] args) {
        /*
        假设你需要用爬虫去淘宝，京东，天猫爬取一个商品的价钱，假设爬虫一要10s，爬虫二要5秒，爬虫三要1秒
        如果是顺序执行的话，需要16s。
        还有一些情况是当某一个爬虫出现了一些问题，就会一直处于等待这个爬虫的状态
        如果使用CompletableFuture的话，将三个任务异步的执行
        最后只需要10s就能给用户返回对比结果
         */

        long start ,end ;

        start = System.currentTimeMillis();
        //存储一个任务，这个任务的返回值是Double
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTM);
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfTB);
        CompletableFuture<Double> futureJD = CompletableFuture.supplyAsync(T06_01_CompletableFuture::priceOfJD);

        //让总体的结果结束的时候在执行。对一堆任务的管理
        CompletableFuture.allOf(futureTM,futureTB,futureJD).join();
        //当有一个任务完成就可以   CompletableFuture.anyOf()

        //CompletableFuture一些有趣的写法,
        //当拿到返回值的时候进行某种（stream式）操作。。。
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
