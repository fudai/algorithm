/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: DFSAlgorithm
 * @package: com.leet.code
 * @date: 2021-08-23 13:41
 * @version: V1.0
 */
package com.leet.code;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @className: DFSAlgorithm
 * @description: 深度优先算法
 * @author: fudai
 * @date: 2021-08-23 13:41
 */
public class DFSAlgorithm {

    public static List<List<Integer>> res = new LinkedList<>();

    /**
     * 输入一组不重复的数字，返回它们的全排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    public static void backtrack(int[] nums, LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (track.contains(num)) {
                continue;
            }
            track.add(num);
            backtrack(nums, track);
            track.removeLast();
        }
    }

    public static List<String[][]> result = new LinkedList<>();

    /**
     * N皇后问题
     *
     * @param n
     * @return
     */
    public static List<String[][]> solveNQueens(int n) {


        String[][] strings = new String[n][n];

        Arrays.fill(strings, ".");

        backtrack(strings, 0);

        return result;
    }

    public static void backtrack(String[][] strings, int i) {

        if (i >= strings.length) {
            result.add(strings);
            return;
        }

        for (int j = 0; j < strings.length; j++) {
            if (!isValid(strings, i, j)) {
                continue;
            }
            strings[i][j] = "Q";
            backtrack(strings, i + 1);
            strings[i][j] = ".";
        }

    }

    public static boolean isValid(String[][] strings, int i, int j) {
        //判断同一行是否已经有
        for (int k = 0; k < i; k++) {
            if ("Q".equals(strings[i][k])) {
                return false;
            }
        }
        //判断左上方是否有
        for (int m = i - 1, n = j - 1; m >= 0 && n >= 0; m--, n--) {
            if ("Q".equals(strings[m][n])) {
                return false;
            }
        }
        //判断右上方是否有
        for (int m = i - 1, n = j + 1; m >= 0 && n <= j; m--, n++) {
            if ("Q".equals(strings[m][n])) {
                return false;
            }
        }
        return true;
    }

    public static List<List<Integer>> res2 = new LinkedList<>();

    /**
     * 子集问题
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, 0, track);
        return res2;
    }

    public static void backtrack(int[] nums, int start, LinkedList<Integer> track) {

        res2.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1, track);
            track.removeLast();
        }

    }

    public static List<List<Integer>> res3 = new LinkedList<>();

    /**
     * 组合问题
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(n, 1, k, track);
        return res3;
    }

    public static void backtrack(int n, int start, int k, LinkedList<Integer> track) {

        if (track.size() == k) {
            res3.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <= n; i++) {
            track.add(i);
            backtrack(n, i + 1, k, track);
            track.removeLast();
        }

    }


    /**
     * nums是否能够被平分为元素和相同的k个子集（元素角度）
     *
     * @param nums
     * @param k
     * @return
     */
    public static boolean canPartitionKSubsets(int[] nums, int k) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;

        int[] bucket = new int[k];

        return backtrack(nums, 0, bucket, target);

    }

    public static boolean backtrack(int[] nums, int index, int[] bucket, int target) {
        if (index == nums.length) {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            return true;
        }

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            bucket[i] = bucket[i] + nums[index];
            if (backtrack(nums, index + 1, bucket, target)) {
                return true;
            }
            bucket[i] = bucket[i] - nums[index];
        }
        return false;
    }

    /**
     * nums是否能够被平分为元素和相同的k个子集（子集角度）
     *
     * @param nums 元素
     * @param k    桶个数
     * @return
     */
    public static boolean canPartitionKSubsets2(int[] nums, int k) {

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;

        boolean[] used = new boolean[nums.length];

        return backtrack2(nums, 0, k, 0, used, target);

    }

    public static boolean backtrack2(int[] nums, int start, int k, int bucket, boolean[] used, int target) {

        if (k == 0) {
            return true;
        }
        //装满一个桶，开始装下一个桶
        if (bucket == target) {
            return backtrack2(nums, 0, k - 1, 0, used, target);
        }
        //从 start 开始向后探查有效的 nums[i] 装入当前桶
        for (int i = start; i < nums.length; i++) {
            //元素已经被使用
            if (used[i]) {
                continue;
            }
            //元素装入桶大于目标值
            if (bucket + nums[i] > target) {
                continue;
            }
            used[i] = true;
            bucket += nums[i];
            if (backtrack2(nums, i + 1, k, bucket, used, target)) {
                return true;
            }
            used[i] = false;
            bucket -= nums[i];
        }
        return false;
    }


}
