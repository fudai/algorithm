/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: SumAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 18:35
 * @version: V1.0
 */
package com.leet.code;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: SumAlgorithm
 * @description: 和相关算法
 * @author: fudai
 * @date: 2021-07-15 18:35
 */
public class SumAlgorithm {



    /**
     * 两数和问题 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 两数和问题 优化解法
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{nums[i], nums[j]};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{};
    }

    /**
     * 三数和问题
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (i - 1 >= 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            while (left < right) {
                if (i != left && i != right && nums[left] + nums[right] == -nums[i]) {
                    while (left + 1 < nums.length - 1 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right - 1 >= 0 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    List<Integer> resultSub = new ArrayList<>();
                    resultSub.add(nums[i]);
                    resultSub.add(nums[left]);
                    resultSub.add(nums[right]);
                    result.add(resultSub);
                    left++;
                }
                if (i == left || nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else if (i == right || nums[left] + nums[right] < -nums[i]) {
                    left++;
                }
            }

        }
        return result;
    }

    /**
     * 四数和问题
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            if (i - 1 >= 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j - 1 >= 0 && nums[j - 1] == nums[j] && i != j - 1) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (i != left && i != right && j != left && j != right && (nums[left] + nums[right] + nums[i] + nums[j]) == target) {
                        while (left + 1 < nums.length - 1 && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (right - 1 >= 0 && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        List<Integer> resultSub = new ArrayList<>();
                        resultSub.add(nums[i]);
                        resultSub.add(nums[j]);
                        resultSub.add(nums[left]);
                        resultSub.add(nums[right]);
                        result.add(resultSub);
                        left++;
                    }
                    if (i == left || j == left || nums[left] + nums[right] + nums[i] + nums[j] > target) {
                        right--;
                    }
                    if (i == right || j == right || nums[left] + nums[right] + nums[i] + nums[j] < target) {
                        left++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * n和问题
     * @param nums
     * @param n
     * @param target
     * @return
     */
    public static List<List<Integer>> nSum(int[] nums, int n, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, n, 0,target);
    }

    public static List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            for (int num : nums) {
                if (num == target) {
                    List<Integer> subResult = new ArrayList<>();
                    subResult.add(num);
                    result.add(subResult);
                    return result;
                }
            }
            return result;
        }
        if (n < 1 || n > nums.length) return result;
        if (n == 2) {
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                int lNum = nums[left];
                int rNum = nums[right];
                if (lNum + rNum == target) {
                    List<Integer> subResult = new ArrayList<>();
                    subResult.add(nums[left]);
                    subResult.add(nums[right]);
                    result.add(subResult);
                    while (left < right && nums[left] == lNum) {
                        left++;
                    }
                    while (left < right && nums[right] == rNum) {
                        right--;
                    }
                } else if (lNum + rNum < target) {
                    while (left < right && nums[left] == lNum) {
                        left++;
                    }
                } else {
                    while (left < right && nums[right] == rNum) {
                        right--;
                    }
                }
            }
        } else {
            int pre = 0;
            for (int i = start; i < nums.length; i++) {
                if (pre == nums[i]) {
                    continue;
                }
                int num = nums[i];
                List<List<Integer>> subResult = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                subResult.forEach(sub -> {
                    sub.add(num);
                    result.add(sub);
                });
                pre = nums[i];
            }
        }
        return result;
    }

    static Map<String, Integer> memo = new HashMap<>();

    /**
     * 目标和（递归方法）
     * @param nums
     * @param i
     * @param target
     * @return
     */
    public static int backUpSum2(int[] nums, int i, int target) {
        if (nums.length == i) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        String key = i + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int result = backUpSum2(nums, i + 1, target - nums[i]) + backUpSum2(nums, i + 1, target + nums[i]);
        memo.put(key, result);
        return result;
    }

    /**
     * 最大子序和（最大和的连续子数组）
     * dp[i] 以nums[i]为结尾的最大子数组和/最长递增子序列为dp[i]
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {

        int[] dp = new int[nums.length];

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 是否可以将非空正整数数组分割成两个子集，使得两个子集的元素和相等。
     * dp[i][j] 前i个数凑成和为j
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum];
    }

    /**
     * 是否可以将非空正整数数组分割成两个子集，使得两个子集的元素和相等。
     * @param nums
     * @return
     */
    public static boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }

    /**
     * 硬币凑数（同一面值不限个数）
     * dp[i][j] 前i种硬币凑成金额j的方式
     * @param amount 目标金额
     * @param coins 硬币面值
     * @return
     */
    public static int change(int amount, int[] coins) {

        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 硬币凑数（同一面值不限个数）
     * @param amount 目标金额
     * @param coins 硬币面值
     * @return
     */
    public static int change2(int amount, int[] coins) {

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }



    /**
     * 目标和（回溯方法）
     * @param nums
     * @param S
     * @return 方法个数
     */
    public static int findTargetSumWays(int[] nums, int S) {
        AtomicInteger result = new AtomicInteger(0);
        backUpSum(nums, 0, S, result);
        return result.get();
    }

    public static void backUpSum(int[] nums, int i, int target, AtomicInteger result) {
        if (nums.length == i) {
            if (target == 0) {
                result.getAndIncrement();
            }
            return;
        }
        //+
        target = target - nums[i];
        backUpSum(nums, i + 1, target,result);
        target = target + nums[i];

        //-
        target = target + nums[i];
        backUpSum(nums, i + 1, target,result);
        target = target - nums[i];
    }
}
