/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: SlidingWindowAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-28 09:19
 * @version: V1.0
 */
package com.leet.code;

import com.common.MonotonicQueue;

import java.util.LinkedList;

/**
 * @className: SlidingWindowAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-28 09:19
 */
public class SlidingWindowAlgorithm {
    /**
     * 滑动窗口最大值
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                queue.push(nums[i]);
            } else {
                queue.push(nums[i]);
                result[i - (k - 1)] = queue.max();
                queue.pop(i - (k - 1));
            }
        }
        return result;
    }

}
