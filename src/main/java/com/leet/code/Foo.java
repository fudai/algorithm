/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Foo
 * @package: com.leet.code
 * @date: 2021-12-13 13:58
 * @version: V1.0
 */
package com.leet.code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: Foo
 * @description:
 * @author: fudai
 * @date: 2021-12-13 13:58
 */
public class Foo {

    private AtomicInteger firstDone = new AtomicInteger(0);

    private AtomicInteger secondDone = new AtomicInteger(0);

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstDone.get() != 1) {

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondDone.get() != 1) {

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
