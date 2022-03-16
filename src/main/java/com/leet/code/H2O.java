/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: H2O
 * @package: com.leet.code
 * @date: 2021-12-20 19:33
 * @version: V1.0
 */
package com.leet.code;

import java.util.concurrent.Semaphore;

/**
 * @className: H2O
 * @description:
 * @author: fudai
 * @date: 2021-12-20 19:33
 */
public class H2O {

    Semaphore h = new Semaphore(2);

    Semaphore o = new Semaphore(0);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
         h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }

    public static void main(String[] args) {
        Runnable releaseHydrogen = new Runnable() {
            @Override
            public void run() {
                System.out.println("H");
            }
        };
        Runnable releaseOxygen = new Runnable() {
            @Override
            public void run() {
                System.out.println("O");
            }
        };

        H2O h2O = new H2O();

        String str = "HHOHHO";
        for(char c:str.toCharArray()){
            if(c=='H'){
                Thread threadH = new Thread(()->{
                    try {
                        h2O.hydrogen(releaseHydrogen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threadH.start();
            }
            if(c=='O'){
                Thread threadO = new Thread(()->{
                    try {
                        h2O.oxygen(releaseOxygen);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                threadO.start();
            }
        }

    }


}
