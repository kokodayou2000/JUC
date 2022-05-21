package com.deng.juc.c_026_01_ThreadPool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class T12_ForkJoinPool {
    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println("---"+ Arrays.stream(nums).sum());
    }
    static class AddTask extends RecursiveAction {
        int start,end;

        AddTask(int s,int e){
            this.start = s;
            this.end = e;
        }

        @Override
        protected void compute() {

            if (end - start <= MAX_NUM){
                long sum = 0;
                for (int i = start; i < end ; i++) {
                    sum += nums[i];
                }
            }else{

                int middle = start +(end - start)/2;

                AddTask subTask1 = new AddTask(start,middle);
                AddTask subTask2 = new AddTask(middle,end);
                subTask1.fork();
                subTask2.fork();
            }
        }
    }
    //继承递归task
    static class AddTaskRet extends RecursiveTask<Long> {
        private static final long serialVersion = 1L;
        int start,end;

        public AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM){
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }

            int middle = start + (end - start)/2;

            //有种分治的感觉了， divide
            AddTaskRet subTask1 = new AddTaskRet(start,middle);
            AddTaskRet subTask2 = new AddTaskRet(middle,end);
            subTask1.fork();
            subTask2.fork();
            //conquer
            return subTask1.join() + subTask2.join();
        }
    }

    public static void main(String[] args) {
        T12_ForkJoinPool temp = new T12_ForkJoinPool();

        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet task =  new AddTaskRet(0,nums.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);

    }

}
