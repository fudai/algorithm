/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: LISAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-21 19:03
 * @version: V1.0
 */
package com.leet.code;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @className: LISAlgorithm
 * @description: 最长递增子序列
 * @author: fudai
 * @date: 2021-07-21 19:03
 */
public class LISAlgorithm {
    /**
     * 最长递增子序列长度
     * dp[i]：以 nums[i] 这个数结尾的最长递增子序列的长度。
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 套娃问题（依赖最长递增子序列）
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        //一维递增，二维递减(因为两个宽度相同的信封不能相互包含的，而逆序排序保证在w相同的数对中最多只选取一个计入 LIS)
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
            }
        });

        int[] height = new int[envelopes.length];

        for (int i = 0; i < height.length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);

    }

}
