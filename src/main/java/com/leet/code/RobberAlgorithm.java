/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: RobberAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 17:59
 * @version: V1.0
 */
package com.leet.code;

import com.common.TreeNode;

import java.util.Arrays;
import java.util.Objects;

/**
 * @className: RobberAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-15 17:59
 */
public class RobberAlgorithm {
    /**
     * 盗贼抢劫（不可以抢劫相连的）递归实现
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, memo, 0);
    }

    /**
     * 盗贼抢劫（不可以抢劫相连的）dp数组
     * @param nums
     * @return
     */
    public static int rob1(int[] nums) {

        int[] dp = new int[nums.length + 2];
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
        }
        return dp[0];
    }

    /**
     * 盗贼抢劫（不可以抢劫相连的）降低空间复杂度
     * @param nums
     * @return
     */
    public static int rob2(int[] nums) {

        int dp_i_1 = 0;
        int dp_i_2 = 0;
        int dp = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp;
        }
        return dp;
    }

    /**
     * 盗贼抢劫（不可以抢劫相连的，首尾也相连）
     * @param nums
     * @return
     */
    public static int rob3(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(dpRange(nums, 1, nums.length - 1), dpRange(nums, 0, nums.length - 2));
    }

    /**
     * 盗贼抢劫（不可以抢劫树中的相连节点）
     * @param treeNode
     * @return
     */
    public static int rob4(TreeNode treeNode) {
        int[] res = dp2(treeNode);
        return Math.max(res[0], res[1]);
    }

    public static int[] dp2(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return new int[]{0, 0};
        }
        int[] left = dp2(treeNode.left);
        int[] rirht = dp2(treeNode.right);
        int rob = treeNode.val + left[0] + rirht[0];
        int noRob = Math.max(left[0], left[1]) + Math.max(rirht[0], rirht[1]);
        return new int[]{noRob, rob};
    }

    /**
     *
     * @param nums
     * @param start 起始位置
     * @param end 截止位置
     * @return
     */
    private static int dpRange(int[] nums, int start, int end) {
        int dp_i_1 = 0;
        int dp_i_2 = 0;
        int dp = 0;
        for (int i = end; i >= start; i--) {
            dp = Math.max(dp_i_1, nums[i] + dp_i_2);
            dp_i_2 = dp_i_1;
            dp_i_1 = dp;
        }
        return dp;
    }

    public static int dp(int[] nums, int[] memo, int i) {
        if (i < 0 || i >= nums.length) {
            return 0;
        }
        if (memo[i] >= 0) {
            return memo[i];
        }
        int result = Math.max(dp(nums, memo, i + 1), nums[i] + dp(nums, memo, i + 2));
        memo[i] = result;
        return result;
    }
}
