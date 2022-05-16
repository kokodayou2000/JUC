package com.deng.juc.c_026_01_ThreadPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class T12_z_ForkJoinTask {
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;
        Fibonacci(int n) { this.n = n;}
        @Override
        protected Integer compute() {
            if (n <= 1)
                return n;
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n-2);
            return f2.compute() + f1.join();
        }
    }


    public static void main(String[] args) {

        Fibonacci fibonacci = new Fibonacci(30);
        ForkJoinPool fjp = new ForkJoinPool();
        fjp.execute(fibonacci);
        long l = fibonacci.join();
        System.out.println(l);
        System.out.println(fibonacci.compute());
    }
}
