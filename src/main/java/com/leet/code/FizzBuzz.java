/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: FizzBuzz
 * @package: com.leet.code
 * @date: 2021-12-23 13:57
 * @version: V1.0
 */
package com.leet.code;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @className: FizzBuzz
 * @description:
 * @author: fudai
 * @date: 2021-12-23 13:57
 */
public class FizzBuzz {

    private Semaphore number= new Semaphore(1);
    private Semaphore fizz= new Semaphore(0);
    private Semaphore buzz= new Semaphore(0);
    private Semaphore fizzbuzz= new Semaphore(0);

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i=1;i<=n;i++){
            if (i % 3 == 0 && i % 5 != 0) {
                fizz.acquire();
                printFizz.run();
                number.release();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i=1;i<=n;i++){
            if (i % 3 != 0 && i % 5 == 0) {
                buzz.acquire();
                printBuzz.run();
                number.release();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i=1;i<=n;i++){
            if (i % 3 == 0 && i % 5 == 0) {
                fizzbuzz.acquire();
                printFizzBuzz.run();
                number.release();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i=1;i<=n;i++){
            number.acquire();
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
                number.release();
            }else if(i % 3 == 0 && i % 5 == 0){
                fizzbuzz.release();
            }else if(i % 3 == 0 && i % 5 != 0){
                fizz.release();
            }else if(i % 3 != 0 && i % 5 == 0){
                buzz.release();
            }
        }
    }

    public static void main(String[] args) {
        Runnable fizz = new Runnable() {
            @Override
            public void run() {
                System.out.println("fizz");
            }
        };
        Runnable buzz = new Runnable() {
            @Override
            public void run() {
                System.out.println("buzz");
            }
        };
        Runnable fizzbuzz = new Runnable() {
            @Override
            public void run() {
                System.out.println("fizzbuzz");
            }
        };
        IntConsumer intConsumer = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println(value);
            }
        };
        FizzBuzz fizzBuzz  = new FizzBuzz(15);
        Thread fizzT = new Thread(()->{
            try {
                fizzBuzz.fizz(fizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread buzzT = new Thread(()->{
            try {
                fizzBuzz.buzz(buzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread fizzbuzzT = new Thread(()->{
            try {
                fizzBuzz.fizzbuzz(fizzbuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread intT = new Thread(()->{
            try {
                fizzBuzz.number(intConsumer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        fizzT.start();
        buzzT.start();
        fizzbuzzT.start();
        intT.start();

    }
}
