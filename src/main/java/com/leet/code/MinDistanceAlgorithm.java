/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: MinDistanceAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 18:44
 * @version: V1.0
 */
package com.leet.code;

import java.util.Objects;

/**
 * @className: MinDistanceAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-15 18:44
 */
public class MinDistanceAlgorithm {
    /**
     * 两个字符串变为相同的最少操作（递归实现）
     * @param s1
     * @param s2
     * @return
     */
    public static int minDistance(String s1, String s2) {
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        Integer[][] memo = new Integer[s1Array.length][s2Array.length];
        return minSub(s1Array, s2Array, s1Array.length - 1, s2Array.length - 1, memo);
    }

    public static int minSub(char[] s1Array, char[] s2Array, int i, int j, Integer[][] memo) {

        if (i < 0) {
            return j + 1;
        }
        if (j < 0) {
            return i + 1;
        }
        if (Objects.nonNull(memo[i][j])) {
            return memo[i][j];
        }
        int min = 0;
        if (s1Array[i] == s2Array[j]) {
            min = minSub(s1Array, s2Array, i - 1, j - 1, memo);
        } else {
            //新增
            int add = minSub(s1Array, s2Array, i, j - 1, memo) + 1;
            //删除
            int delete = minSub(s1Array, s2Array, i - 1, j, memo) + 1;
            //替换
            int replace = minSub(s1Array, s2Array, i - 1, j - 1, memo) + 1;
            min = Math.min(Math.min(add, delete), replace);
        }
        memo[i][j] = min;
        return min;
    }

    /**
     * 两个字符串变为相同的最少操作（动态规划实现）
     * dp[i][j] 以i和j结尾的子串最小编辑距离
     * @param s1
     * @param s2
     * @return
     */
    public static int minDistance2(String s1, String s2) {
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        int[][] dp = new int[s1Array.length + 1][s2Array.length + 1];
        for (int i = 1; i <= s1Array.length; i++) {
            dp[i][0] = i;

        }
        for (int j = 1; j <= s2Array.length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1Array.length; i++) {
            for (int j = 1; j <= s2Array.length; j++) {
                if (s1Array[i - 1] == s2Array[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[s1Array.length][s2Array.length];
    }

    /**
     * 编辑距离问题
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance3(String word1, String word2) {
        char[] array1 = word1.toCharArray();
        char[] array2 = word2.toCharArray();
        int[][] dp = new int[array1.length + 1][array2.length + 1];
        for (int i = 1; i < array1.length + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < array2.length + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= array1.length; i++) {
            for (int j = 1; j <= array2.length; j++) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[array1.length][array2.length];
    }
}
