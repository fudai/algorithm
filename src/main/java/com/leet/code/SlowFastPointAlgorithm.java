/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: SlowFastPointAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-28 09:24
 * @version: V1.0
 */
package com.leet.code;

import com.common.ListNode;

import java.util.Objects;

/**
 * @className: SlowFastPointAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-28 09:24
 */
public class SlowFastPointAlgorithm {
    /**
     * 删除有序数据的重复元素
     *
     * @param nums
     * @return 去除重复元素后的个数
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0;
        int fast = 1;
        int n = nums.length;
        while (fast < n) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    /**
     * 删除有序链表的重复节点
     * 1 1 2 3 4
     * s
     * f
     *
     * @param node 节点
     * @return 去除重复节点后的链表
     */
    public static ListNode deleteDuplicates(ListNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        ListNode slow = node;
        ListNode fast = node;
        while (Objects.nonNull(fast)) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return node;
    }

    /**
     * 移除指定元素
     *
     * @param nums
     * @param val  指定值
     * @return 返回移除后的数据长度
     */
    public static int removeElement(int[] nums, int val) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * 将所有0元素移动到末尾
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }
}
