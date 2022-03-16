/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: MonotonicQueue
 * @package: com.common
 * @date: 2021-07-28 09:25
 * @version: V1.0
 */
package com.common;

import java.util.LinkedList;

/**
 * @className: MonotonicQueue
 * @description: 单调队列（从大到小递减）
 * @author: fudai
 * @date: 2021-07-28 09:25
 */
public class  MonotonicQueue {
    LinkedList<Integer> q = new LinkedList<>();

    public void push(int n) {
        // 将小于 n 的元素全部删除
        while (!q.isEmpty() && q.getLast() < n) {
            q.pollLast();
        }
        // 然后将 n 加入尾部
        q.addLast(n);
    }

    public int max() {
        return q.getFirst();
    }

    public void pop(int n) {
        if (n == q.getFirst()) {
            q.pollFirst();
        }
    }
}
