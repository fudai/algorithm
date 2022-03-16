/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: TwoSplitSearchAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-28 09:15
 * @version: V1.0
 */
package com.leet.code;

/**
 * @className: TwoSplitSearchAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-28 09:15
 */
public class TwoSplitSearchAlgorithm {
    /**
     * 二分查找（单个）
     *
     * @param nums   升序数组
     * @param target 目标数字
     * @return 满足的下标
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查出（范围）
     *
     * @param nums   递增数组（可重复）
     * @param target 目标数字
     * @return 满足的下标范围
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int[] result = {-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) right = mid - 1;
            if (nums[mid] > target) right = mid - 1;
            if (nums[mid] < target) left = mid + 1;
        }
        result[0] = left >= nums.length || nums[left] != target ? -1 : left;

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) left = mid + 1;
            if (nums[mid] > target) right = mid - 1;
            if (nums[mid] < target) left = mid + 1;
        }
        result[1] = right >= nums.length || nums[right] != target ? -1 : right;
        return result;
    }
}
