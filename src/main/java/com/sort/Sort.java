/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: Sort
 * @package: com.sort
 * @date: 2021-07-06 19:03
 * @version: V1.0
 */
package com.sort;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import jdk.nashorn.internal.ir.Node;
import lombok.Synchronized;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: Sort
 * @description:
 * @author: fudai
 * @date: 2021-07-06 19:03
 */
public class Sort {

    /**
     * 快速排序
     *
     * @param nums
     * @param left
     * @param right
     */
    public void quickSort(int[] nums, int left, int right) {
        if (left >= right) return;
        int p = quick(nums, left, right);
        quickSort(nums, left, p - 1);
        quickSort(nums, p + 1, right);
    }

    public int quick(int[] nums, int left, int right) {
        if (left >= right) {
            return left;
        }
        int target = nums[left];
        while (left < right) {
            while (left < right && nums[left] < target) {
                left++;
            }
            while (left < right && nums[right] > target) {
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        nums[left] = target;
        return left;
    }

    @Test
    public void quickSortTest() {
        int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 归并排序
     *
     * @param nums
     * @param left
     * @param right
     */
    public void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        int[] numsNew = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                numsNew[k++] = nums[i];
                i++;
            } else {
                numsNew[k++] = nums[j];
                j++;
            }
        }
        while (i <= mid) {
            numsNew[k++] = nums[i];
            i++;
        }
        while (j <= right) {
            numsNew[k++] = nums[j];
            j++;
        }
        int p = 0;
        for (int q = left; q <= right; q++) {
            nums[q] = numsNew[p++];
        }
    }

    @Test
    public void mergerSortTest() {
        int[] nums = {9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 冒泡排序
     *
     * @param nums
     */
    public synchronized void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
    }

    @Test
    public void bubbleSortTest() {
        int[] nums = {9, 8, 8, 7, 6, 5, 4, 3, 2, 1, 1};
        bubbleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

}
