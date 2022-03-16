/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: JumpAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-28 09:18
 * @version: V1.0
 */
package com.leet.code;

/**
 * @className: JumpAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-28 09:18
 */
public class JumpAlgorithm {
    /**
     * 跳跃游戏（返回是否可以跳跃到底）
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int maxJump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if (maxJump <= i) return false;
        }
        return maxJump >= nums.length - 1;
    }


    /**
     * 跳跃游戏 II（返回最小跳跃步数）
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int end = 0;
        int maxJump = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxJump = Math.max(maxJump, i + nums[i]);
            if (i == end) {
                step++;
                end = maxJump;
            }
        }
        return step;
    }
}
