/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: FindKthLargestAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-28 09:21
 * @version: V1.0
 */
package com.leet.code;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @className: FindKthLargestAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-28 09:21
 */
public class FindKthLargestAlgorithm {
    /**
     * 寻找第K大数（二叉堆解法）最小堆
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest1(int[] nums, int k) {
        if (nums.length < 1 || k < 1) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    /**
     * 寻找第K大数（快速选择解法）
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest2(int[] nums, int k) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int p = partition(nums, left, right);
            if (p == nums.length - k) {
                return nums[p];
            } else if (p > nums.length - k) {
                right = p - 1;
            } else {
                left = p + 1;
            }
        }
        return -1;
    }

    /**
     * 快速排序子方法
     * @param nums 数组
     * @param left 左下标
     * @param right 右下标
     * @return 分界点下标
     */
    public static int partition(int[] nums, int left, int right) {
        if (left == right) return left;
        int p = nums[left];
        while (left < right) {
            while (nums[right] > p && left < right) {
                right--;
            }
            while (nums[left] < p && left < right) {
                left++;
            }
            if (left >= right) {
                break;
            }
            swap(nums, left, right);
        }
        nums[left] = p;
        return left;
    }

    /**
     * 交换数据
     * @param nums 数组
     * @param left 左下标
     * @param right 右下标
     */
    private static void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    public static int partition2(int[] nums, int lo, int hi) {
        if (lo == hi) return lo;
        // 将 nums[lo] 作为默认分界点 pivot
        int pivot = nums[lo];
        // j = hi + 1 因为 while 中会先执行 --
        int i = lo, j = hi + 1;
        while (true) {
            // 保证 nums[lo..i] 都小于 pivot
            while (nums[++i] < pivot) {
                if (i == hi) break;
            }
            // 保证 nums[j..hi] 都大于 pivot
            while (nums[--j] > pivot) {
                if (j == lo) break;
            }
            if (i >= j) break;
            // 如果走到这里，一定有：
            // nums[i] > pivot && nums[j] < pivot
            // 所以需要交换 nums[i] 和 nums[j]，
            // 保证 nums[lo..i] < pivot < nums[j..hi]
            swap(nums, i, j);
        }
        // 将 pivot 值交换到正确的位置
        swap(nums, j, lo);
        // 现在 nums[lo..j-1] < nums[j] < nums[j+1..hi]
        return j;
    }

    /**
     * 快速排序
     * @param nums
     * @return
     */
    public static int[] quickSort(int[] nums) {
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void sort(int[] nums, int left, int right) {
        if (left >= right) return;
        int p = partition(nums, left, right);
        sort(nums, left, p - 1);
        sort(nums, p + 1, right);
    }


}
