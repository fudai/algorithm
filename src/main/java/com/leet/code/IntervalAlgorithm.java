/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: IntervalAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 18:34
 * @version: V1.0
 */
package com.leet.code;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @className: IntervalAlgorithm
 * @description: 区间相关算法
 * @author: fudai
 * @date: 2021-07-15 18:34
 */
public class IntervalAlgorithm {
    /**
     * 删除被覆盖区间
     * @param intervals
     * @return 删除后的区间个数
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = intervals[i];
            if (left <= temp[0] && temp[1] <= right) {
                result++;
            } else if (left <= temp[0] && right < temp[1]) {
                right = temp[1];
            } else if (temp[0] > right) {
                left = temp[0];
                right = temp[1];
            }
        }
        return intervals.length - result;

    }

    /**
     * 合并重叠区间
     * @param intervals
     * @return
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[][]{};
        }
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        int[][] result = new int[intervals.length][2];
        int k = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = intervals[i];
            if (temp[0] <= right) {
                right = Math.max(temp[1], right);
            } else {
                result[k][0] = left;
                result[k][1] = right;
                left = temp[0];
                right = temp[1];
                k++;
            }
            if (i == intervals.length - 1) {
                result[k][0] = left;
                result[k][1] = right;
            }
        }
        return Arrays.copyOfRange(result, 0, k);
    }

    /**
     * 区间交集问题
     * @param A
     * @param B
     * @return 区间交集集合
     */
    public static int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[][]{};
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int[][] result = new int[A.length + B.length][2];
        while (i < A.length && j < B.length) {
            int a1 = A[i][0];
            int a2 = A[i][1];
            int b1 = B[j][0];
            int b2 = B[j][1];
            if (a2 >= b1 && a1 <= b2) {
                result[k][0] = Math.max(a1, b1);
                result[k][1] = Math.min(a2, b2);
                k++;
            }
            if (a2 > b2) {
                j++;
            } else {
                i++;
            }
        }
        return Arrays.copyOfRange(result, 0, k + 1);

    }

    /**
     * 最大无重叠区间数
     * @param intervals
     * @return
     */
    public static int intervalSchedule(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparing((a -> a[1])));
        int x_end = intervals[0][1];
        int count = 1;
        for (int[] a : intervals) {
            int start = a[0];
            if (start >= x_end) {
                count++;
                x_end = a[1];
            }
        }
        return count;
    }

    /**
     * 给出n个区间[L,R]，要求删除最少的区间，使得任意两个区间都没有重叠部分
     * @param intervals
     * @return
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        return intervals.length - intervalSchedule(intervals);
    }

    /**
     * 用最少的箭头射爆气球
     * @param points
     * @return 最少的箭头
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparing((a -> a[1])));
        int x_end = points[0][1];
        int count = 1;
        for (int[] a : points) {
            int start = a[0];
            if (start > x_end) {
                count++;
                x_end = a[1];
            }
        }
        return count;
    }
}
