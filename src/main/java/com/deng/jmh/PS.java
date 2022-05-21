package com.deng.jmh;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PS {
    static List<Integer> nums = new ArrayList<>();
    static {
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            nums.add(1000000+r.nextInt(1000000));
        }
    }

    public static void foreach() {
        nums.forEach(PS::isPrime);
    }

    public static void parallel(){
        nums.parallelStream().forEach(PS::isPrime);
    }

    static boolean isPrime(int nums){
        for(int i = 2; i<=nums/2;i++){
            if (nums % i == 0) return false;
        }
        return true;
    }

}
