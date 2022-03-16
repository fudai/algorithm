/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: MaxCoinsAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-28 09:18
 * @version: V1.0
 */
package com.leet.code;

/**
 * @className: MaxCoinsAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-28 09:18
 */
public class MaxCoinsAlgorithm {
    /**
     * 戳气球
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。
     * 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * dp[i][j]表示戳破气球i和气球j之间（开区间，不包括i和j）的所有气球，可以获得的最高分数
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int[] point = new int[nums.length + 2];
        point[0] = point[nums.length + 1] = 1;
        for (int i = 1; i < point.length - 1; i++) {
            point[i] = nums[i - 1];
        }
        int[][] dp = new int[point.length][point.length];
        for (int i = point.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < point.length; j++) {
                for (int k = i + 1; k < j; k++) {
                    //如果最后一个戳破气球k
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + point[k] * point[i] * point[j]);
                }
            }
        }
        return dp[0][point.length - 1];
    }
}
