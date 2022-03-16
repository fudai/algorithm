/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: SuperEggDropAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 18:37
 * @version: V1.0
 */
package com.leet.code;

/**
 * @className: SuperEggDropAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-15 18:37
 */
public class SuperEggDropAlgorithm {
    /**
     * 高楼扔鸡蛋问题（递归解法，二分查找优化）
     * @param K 鸡蛋个数
     * @param N 楼层数
     * @return
     */
    public static int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return dpEgg2Split(K, N, dp);
    }

    /**
     * 高楼扔鸡蛋问题（迭代解法）
     * @param K
     * @param N
     * dp[K][m] :K个鸡蛋，最坏情况下最多扔m次可以确认的楼层数
     * 时间复杂度：O(K*N)
     * @return
     */
    public static int superEggDrop2(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }

    /**
     * 高楼扔鸡蛋问题（递归解法）
     * @param K
     * @param N
     * @param dp K N ：K个鸡蛋，N层楼，最坏情况下的扔鸡蛋次数确定临界楼层（鸡蛋在这一层正好没摔碎）
     * 时间复杂度：O(K*N*N)
     * @return
     */
    private int dpEgg(int K, int N, int[][] dp) {
        if (K == 1) {
            dp[K][N] = N;
            return N;
        }
        if (N == 0 || K == 0) {
            dp[K][N] = 0;
            return 0;
        }
//        if (N == 1 && K >= 1) {
//            dp[K][N] = 0;
//            return 0;
//        }
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            result = Math.min(result, Math.max(dpEgg(K, N - i, dp), dpEgg(K - 1, i - 1, dp)) + 1);
        }
        dp[K][N] = result;
        return result;
    }

    /**
     * 高楼扔鸡蛋问题（二分解法）
     * @param K
     * @param N
     * @param dp K N ：K个鸡蛋，N层楼，最坏情况下的扔鸡蛋次数确定临界楼层（鸡蛋在这一层正好没摔碎）
     * 时间复杂度：O(K*N*logN)
     * @return
     */
    private static int dpEgg2Split(int K, int N, int[][] dp) {
        if (K == 1) {
            dp[K][N] = N;
            return N;
        }
        if (N == 0 || K == 0) {
            dp[K][N] = 0;
            return 0;
        }
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        int result = Integer.MAX_VALUE;
        int left = 1;
        int right = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            int noResult = dpEgg2Split(K, N - mid, dp);
            int yesResult = dpEgg2Split(K - 1, mid - 1, dp);
            if (noResult > yesResult) {
                left = mid + 1;
                result = Math.min(result, noResult + 1);
            } else {
                right = mid - 1;
                result = Math.min(result, yesResult + 1);
            }
        }
        dp[K][N] = result;
        System.out.println("K:" + K + " N:" + N + "  result:" + result);
        return result;
    }
}
