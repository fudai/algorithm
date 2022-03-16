/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: PrintNumThread
 * @package: com
 * @date: 2021-11-17 20:20
 * @version: V1.0
 */
package com;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: PrintNumThread
 * @description:
 * @author: fudai
 * @date: 2021-11-17 20:20
 */
public class PrintNumThread extends Thread {


    AtomicInteger num;

    Semaphore semaphore;

    /**
     * 0 偶数线程 ，1 奇数线程
     */
    int flag;

    public PrintNumThread(AtomicInteger num, Semaphore semaphore, int flag) {
        this.num = num;
        this.semaphore = semaphore;
        this.flag = flag;
    }


    @Override
    public void run() {
        while (num.get() <= 10) {
            if (num.get() % 2 == flag) {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(flag + ":" + num);
                num.incrementAndGet();
                semaphore.release();
            }
        }

    }

}
