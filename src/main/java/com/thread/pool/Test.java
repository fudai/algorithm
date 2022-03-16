/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Test
 * @package: com.thread.pool
 * @date: 2021-11-15 17:59
 * @version: V1.0
 */
package com.thread.pool;

import com.PrintNumThread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @className: Testø
 * @description:
 * @author: fudai
 * @date: 2021-11-15 17:59
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
//        char[] chars = new char[]{'a', 'l', 'i', 'b', 'a', 'b', 'a'};
//        int index = 2;
//        System.out.println(swap(chars, index));

//            1     5    9       1+i(k+k-2)
//            2  4  6  8 10      2+i(k+1)/2
//            3     7    11      1+i(k+1)
//
//            1    7       13    1+i(k+k-2)
//            2  6 8    12 14
//            3 5  9  11   15
//            4    10      16
//        System.out.println(fun(5));
//        System.out.println(fun2(5));
        print();

//        Thread.sleep(100000);

    }

//    @org.junit.Test
//    public void swap(char[] chars, int index) {
//        if (index < chars.length / 2) {
//            int k = index;
//            for (int i = 0; i < index; i++) {
//                char temp = chars[i];
//                chars[i] = chars[index + 1];
//                chars[k + 1] = temp;
//                k++;
//            }
//        } else {
//
//        }
//    }

//    @org.junit.Test
//    public void test(){
//        char[] chars = new char[]{'a','l','i','b','a','b','a'};
//        int index = 4;
//        System.out.println(swap2(chars,index));
//    }


    public static char[] swap(char[] chars, int index) {
        char[] charsNew = new char[chars.length];
        int k = index;
        int i = 0;
        while (k < chars.length - 1) {
            charsNew[i] = chars[k + 1];
            k++;
            i++;
        }
        int j = 0;
        while (j <= index) {
            charsNew[i] = chars[j];
            j++;
            i++;
        }
        return charsNew;
    }


    public static void print() {
        Semaphore semaphore = new Semaphore(2);
        AtomicInteger k = new AtomicInteger(1);
        new PrintNumThread(k, semaphore, 1).start();

        new PrintNumThread(k, semaphore, 0).start();

    }

    public static int fun(int i) {
        if (i < 0) {
            throw new RuntimeException("非法入参");
        }
        if (i == 0 || i == 1) {
            return 1;
        }
        return fun(i - 1) + fun(i - 2);
    }

    public static int fun2(int i){
        if(i ==0 ){
            return 1;
        }
        if(i ==1 ){
            return 1;
        }
        int[] dp = new int[i];
        dp[0]=dp[1]=1;
        for(int k=2;k<i;k++){
            dp[k] = dp[k-1]+dp[k-2];
        }
        return dp[i-1];
    }
}
