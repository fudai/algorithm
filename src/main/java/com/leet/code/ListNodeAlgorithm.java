/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: ListNodeAlgorithm
 * @package: com.leet.code
 * @date: 2021-08-19 09:34
 * @version: V1.0
 */
package com.leet.code;

import com.common.ListNode;

/**
 * @className: ListNodeAlgorithm
 * @description: 链表相关算法
 * @author: fudai
 * @date: 2021-08-19 09:34
 */
public class ListNodeAlgorithm {


    public static ListNode left;

    /**
     * 是否回文链表（后序遍历解法）
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {

        left = head;

        return traverse(head);

    }


    public static boolean traverse(ListNode node) {
        if (node == null) {
            return true;
        }
        boolean res = traverse(node.next);
        res = res && left.val == node.val;
        left = left.next;
        return res;
    }


    /**
     * 是否回文链表（链表全部反转解法）
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        ListNode reverseNode = reverse(head);
        while (head!=null&&reverseNode!=null){
            if(head.val!=reverseNode.val){
                return false;
            }
            head =head.next;
            reverseNode = reverseNode.next;
        }
        return true;
    }

    /**
     * 链表反转
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 是否回文链表（快慢指针解法）
     *
     * @param head
     * 1 2 3 4 5
     *     s
     *         f
     * @return
     */
    public static boolean isPalindrome3(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode part = reverse(slow);
        while(part!=null){
            if (head.val != part.val) {
                return false;
            }
            head = head.next;
            part = part.next;
        }
        return true;
    }


}
