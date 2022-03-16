/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: StockTradeAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-14 11:14
 * @version: V1.0
 */
package com.leet.code;

/**
 * @className: StockTradeAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-14 11:14
 */
public class StockTradeAlgorithm {

/**
 * for（i ）
 *  for（j ）
 *     for（ k ）
 *     i:天数
 *     j:至今最多交易次数
 *     k:是否持有
 *     dp[i][j][0]=max(dp[i-1][j][0],dp[i-1][j][1]+price[i])
 *     dp[i][j][1]=max(dp[i-1][j-1][0]-price[i],dp[i-1][j][1])
 */

    /**
     * 买卖该股票一次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dpTable[i][0] = 0;
                dpTable[i][1] = -prices[i];
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], -prices[i]);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 买卖该股票一次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public static int maxProfitOpt(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int dp0 = 0;
        int dp1 = 0;
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dp0 = 0;
                dp1 = -prices[i];
                continue;
            }
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, -prices[i]);
        }
        return dp0;
    }


    /**
     * 买卖该股票不限制次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dpTable[i][0] = 0;
                dpTable[i][1] = -prices[i];
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], dpTable[i - 1][0] - prices[i]);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 买卖该股票不限制次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public static int maxProfit2Opt(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int dp0 = 0;
        int dp1 = -prices[0];
        for (int i = 1; i < days; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }
        return dp0;
    }

    /**
     * 买卖该股票2次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int k = 3;
        int[][][] dpTable = new int[days][k][2];
        for (int i = 0; i < days; i++) {
            if (i == 0) {
                dpTable[i][1][0] = 0;
                dpTable[i][1][1] = -prices[i];
                dpTable[i][2][0] = 0;
                dpTable[i][2][1] = -prices[i];
                continue;
            }
            dpTable[i][1][0] = Math.max(dpTable[i - 1][1][0], dpTable[i - 1][1][1] + prices[i]);
            dpTable[i][1][1] = Math.max(dpTable[i - 1][1][1], -prices[i]);
            dpTable[i][2][0] = Math.max(dpTable[i - 1][2][0], dpTable[i - 1][2][1] + prices[i]);
            dpTable[i][2][1] = Math.max(dpTable[i - 1][2][1], dpTable[i - 1][1][0] - prices[i]);
            System.out.println(i + ": " + dpTable[i][1][0] + " " + dpTable[i][1][1] + " " + dpTable[i][2][0] + " " + dpTable[i][2][1]);
        }
        return dpTable[days - 1][2][0];
    }

    /**
     * 买卖该股票k次可能获得的最大利润
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int k, int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        if (k == 0) return 0;
        int[][][] dpTable = new int[days][k + 1][2];
        for (int i = 0; i < days; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dpTable[0][j][0] = 0;
                    dpTable[0][j][1] = -prices[i];
                    continue;
                }
                dpTable[i][j][0] = Math.max(dpTable[i - 1][j][0], dpTable[i - 1][j][1] + prices[i]);
                dpTable[i][j][1] = Math.max(dpTable[i - 1][j][1], dpTable[i - 1][j - 1][0] - prices[i]);
//                System.out.println("i:" + i + " j:" + j + " 0    " + dpTable[i][j][0]);
//                System.out.println("i:" + i + " j:" + j + " 1    " + dpTable[i][j][1]);
            }
        }
        return dpTable[days - 1][k][0];

    }

    /**
     * 买卖该股票k次可能获得的最大利润（降低空间复杂度）
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int k, int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        if (k == 0) return 0;
        k = Math.min(k, days / 2);
        int dp_i_j_0 = 0;
        int dp_i_j_1 = 0;
        int dp_i_j_pre_0 = 0;
        for (int i = 0; i < days; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp_i_j_0 = 0;
                    dp_i_j_1 = -prices[i];
                    continue;
                }
                dp_i_j_pre_0 = dp_i_j_0;
                dp_i_j_0 = Math.max(dp_i_j_0, dp_i_j_1 + prices[i]);
                dp_i_j_1 = Math.max(dp_i_j_1, dp_i_j_pre_0 - prices[i]);
            }
        }
        return dp_i_j_0;

    }

    /**
     * 股票买卖最大收益（可以进行无数次交易，每次卖之后要等一天才可以交易）
     *
     * @param prices
     * @return
     */
    public static int maxProfit5(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 == -1) {
                dpTable[0][0] = 0;
                dpTable[0][1] = -prices[0];
                continue;
            }
            if (i - 1 == 0) {
                dpTable[1][0] = Math.max(dpTable[0][0], dpTable[0][1] + prices[1]);
                dpTable[1][1] = Math.max(dpTable[0][1], -prices[1]);
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], dpTable[i - 2][0] - prices[i]);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 股票买卖最大收益（可以进行无数次交易，每次交易要支付手续费）
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit6(int[] prices, int fee) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dpTable[i][0] = 0;
                dpTable[i][1] = -prices[i] - fee;
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], dpTable[i - 1][0] - prices[i] - fee);
        }
        return dpTable[days - 1][0];
    }
}