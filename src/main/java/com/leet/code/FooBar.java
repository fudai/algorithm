/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: FooBar
 * @package: com.leet.code
 * @date: 2021-12-14 19:43
 * @version: V1.0
 */
package com.leet.code;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: FooBar
 * @description:
 * @author: fudai
 * @date: 2021-12-14 19:43
 */
public class FooBar {

    Semaphore semaphoreFoo = new Semaphore(1);
    Semaphore semaphoreBar = new Semaphore(0);

    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    volatile boolean fooExe = true;

    private int n;

    ReentrantLock lock = new ReentrantLock(true);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphoreFoo.acquire();
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            semaphoreBar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            semaphoreBar.acquire();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            semaphoreFoo.release();
        }
    }

    public void foo2(Runnable printFoo) throws BrokenBarrierException, InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!fooExe) {

            }
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            fooExe = false;
            cyclicBarrier.await();
        }
    }

    public void bar2(Runnable printBar) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n; i++) {
            cyclicBarrier.await();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            fooExe = true;
        }
    }

    public void foo3(Runnable printFoo) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n; ) {
            if (fooExe) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                fooExe = false;
                i++;
            } else {
                Thread.yield();
            }
        }
    }

    public void bar3(Runnable printBar) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n; ) {
            if (!fooExe) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                fooExe = true;
                i++;
            } else {
                Thread.yield();
            }
        }
    }

    public void foo4(Runnable printFoo) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n;) {
            lock.lock();
            try{
                if (fooExe) {
                    // printFoo.run() outputs "foo". Do not change or remove this line.
                    printFoo.run();
                    fooExe = false;
                    i++;
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public void bar4(Runnable printBar) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if (!fooExe) {
                    // printBar.run() outputs "bar". Do not change or remove this line.
                    printBar.run();
                    fooExe = true;
                    i++;
                }
            } finally {
                lock.unlock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        test();
    }

    private static void test() {
        FooBar fooBar = new FooBar(2);
        Runnable printFoo = new Runnable(){
            @Override
            public void run() {
                System.out.println("foo");
            }
        };
        Runnable printBar = new Runnable(){
            @Override
            public void run() {
                System.out.println("bar");
            }
        };
        Thread threadFoo = new Thread(() -> {
            try {
                fooBar.foo4(printFoo);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        Thread threadBar= new Thread(() -> {
            try {
                fooBar.bar4(printBar);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        threadFoo.start();
        threadBar.start();
    }

}
