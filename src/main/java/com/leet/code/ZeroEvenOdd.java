/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: ZeroEvenOdd
 * @package: com.leet.code
 * @date: 2021-12-22 13:35
 * @version: V1.0
 */
package com.leet.code;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @className: ZeroEvenOdd
 * @description:
 * @author: fudai
 * @date: 2021-12-22 13:35
 */
public class ZeroEvenOdd {

    private int n;

    private Semaphore zero = new Semaphore(1);

    private Semaphore even = new Semaphore(0);

    private Semaphore odd = new Semaphore(0);


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            zero.acquire();
            printNumber.accept(0);
            if (i % 2 == 0) {
                odd.release();
            } else {
                even.release();
            }

        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i = i + 2) {
            even.acquire();
            printNumber.accept(i);
            zero.release();
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i = i + 2) {
            odd.acquire();
            printNumber.accept(i);
            zero.release();
        }

    }

    public static void main(String[] args) {
        IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
            }
        };
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(10);
        Thread zeroT = new Thread(()->{
            try {
                zeroEvenOdd.zero(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread oddT = new Thread(()->{
            try {
                zeroEvenOdd.odd(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread evenT = new Thread(()->{
            try {
                zeroEvenOdd.even(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        zeroT.start();
        oddT.start();
        evenT.start();

    }
}
