/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Array
 * @package: com.leet.code
 * @date: 2021-10-25 21:22
 * @version: V1.0
 */
package com.leet.code;

import org.junit.Test;

/**
 * @className: Array
 * @description:
 * @author: fudai
 * @date: 2021-10-25 21:22
 */
public class Array {

    public static int reverse(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return array[i + 1];
            }
        }
        return -1;
    }

//    public static int reverse2(int[] array) {
//
//    }

    public static int search(int[] nums) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= nums[right]) {
                left = mid + 1;
            }
            if (nums[mid] <= nums[left]) {
                right = mid - 1;
            }
        }
        return left > nums.length - 1 ? -1 : nums[left];
    }
}
