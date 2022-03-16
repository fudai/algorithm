/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: BackpackAlgorithm
 * @package: com.leet.code
 * @date: 2021-08-12 19:04
 * @version: V1.0
 */
package com.leet.code;

/**
 * @className: BackpackAlgorithm
 * @description: 背包问题算法
 * @author: fudai
 * @date: 2021-08-12 19:04
 */
public class BackpackAlgorithm {

    /**
     * 0-1背包问题
     * dp[i][j]  i个物品，容量为j 的最大价值
     * @param w      背包容量
     * @param weight 物品重量
     * @param value  物品价值
     * @return
     */

    public static int backpack(int w, int[] weight, int[] value) {

        int[][] dp = new int[weight.length + 1][w + 1];

        for (int i = 0; i < weight.length + 1; i++) {
            dp[i][0] = 0;
        }

        for (int j = 0; j < w + 1; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= weight.length; i++) {
            for (int j = 0; j <= w; j++) {
                if (weight[i-1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i-1]] + value[i-1]);
                }
            }

        }
        return dp[weight.length][w];
    }
}
