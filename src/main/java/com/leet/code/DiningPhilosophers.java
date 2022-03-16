/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: DiningPhilosophers
 * @package: com.leet.code
 * @date: 2021-12-24 10:05
 * @version: V1.0
 */
package com.leet.code;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

/**
 * @className: DiningPhilosophers
 * @description:
 * @author: fudai
 * @date: 2021-12-24 10:05
 */
public class DiningPhilosophers {

    Semaphore fork0 = new Semaphore(1);
    Semaphore fork1 = new Semaphore(1);
    Semaphore fork2 = new Semaphore(1);
    Semaphore fork3 = new Semaphore(1);
    Semaphore fork4 = new Semaphore(1);
    private Semaphore eatLimit = new Semaphore(4);

    public DiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        eatLimit.acquire();
        switch (philosopher){
            case 0:
                fork0.acquire();
                fork1.acquire();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork0.release();
                fork1.release();
                break;
            case 1:
                fork1.acquire();
                fork2.acquire();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork1.release();
                fork2.release();
                break;
            case 2:
                fork2.acquire();
                fork3.acquire();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork2.release();
                fork3.release();
                break;
            case 3:
                fork3.acquire();
                fork4.acquire();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork3.release();
                fork4.release();
                break;
            case 4:
                fork4.acquire();
                fork0.acquire();
                pickLeftFork.run();
                pickRightFork.run();
                eat.run();
                putLeftFork.run();
                putRightFork.run();
                fork4.release();
                fork0.release();
                break;
        }
        eatLimit.release();
    }

    public static void main(String[] args) {

        DiningPhilosophers diningPhilosophers = new DiningPhilosophers();

        PhilosopherThread T0 = new PhilosopherThread(0, diningPhilosophers);
        PhilosopherThread T1 = new PhilosopherThread(1, diningPhilosophers);
        PhilosopherThread T2 = new PhilosopherThread(2, diningPhilosophers);
        PhilosopherThread T3 = new PhilosopherThread(3, diningPhilosophers);
        PhilosopherThread T4 = new PhilosopherThread(4, diningPhilosophers);

        T0.start();
        T1.start();
        T2.start();
        T3.start();
        T4.start();


    }

    static class PickRunnable implements Runnable{

        int[] behavior;

        public PickRunnable(int[] behavior) {
            this.behavior = behavior;
        }

        @Override
        public void run() {
            System.out.println(Arrays.toString(behavior));
        }
    }

    static class PhilosopherThread extends Thread{
        int philosopher;

        DiningPhilosophers diningPhilosophers;

        public PhilosopherThread(int philosopher, DiningPhilosophers diningPhilosophers) {
            this.philosopher = philosopher;
            this.diningPhilosophers = diningPhilosophers;
        }

        @Override
        public void run() {
            Runnable pickLeftFork = new PickRunnable(new int[]{philosopher, 1, 1});
            Runnable pickRightFork = new PickRunnable(new int[]{philosopher, 2, 1});
            Runnable eat = new PickRunnable(new int[]{philosopher, 0, 3});
            Runnable putLeftFork = new PickRunnable(new int[]{philosopher, 1, 2});
            Runnable putRightFork = new PickRunnable(new int[]{philosopher, 2, 2});
            try {
                diningPhilosophers.wantsToEat(philosopher, pickLeftFork,pickRightFork,eat,putLeftFork,putRightFork);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
