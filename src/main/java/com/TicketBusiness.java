/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Test
 * @package: com
 * @date: 2021-10-19 19:08
 * @version: V1.0
 */
package com;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: TicketBusiness
 * @description:
 * @author: fudai
 * @date: 2021-10-19 19:08
 */
public class TicketBusiness {

    /**
     * 公共已卖票编号
     */
    private AtomicInteger tickets = new AtomicInteger(0);

    /**
     * 初始票价
     */
    private int price = 500;

    /**
     * 票总数阈值
     */
    private int ticketMax = 1000;

    @org.junit.Test
    public void ticketSell() {
        new Worker("黄牛A").start();
        new Worker("黄牛B").start();
        new Worker("黄牛C").start();
    }

    public class Worker extends Thread {
        private int profit = 0;
        private List<Integer> ticketNoList = new ArrayList<>();
        private String name;

        public Worker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            int ticketNo = tickets.getAndIncrement();
            while (ticketNo < ticketMax) {
                ticketNoList.add(ticketNo);
                profit = profit + (price + (ticketNo / 100) * 100);
                ticketNo = tickets.getAndIncrement();
            }
            System.out.println(name + "卖票总收入：" + profit + ",卖票编号：" + ticketNoList.toString());
        }
    }
}
