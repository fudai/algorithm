/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: LinkedListAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-14 11:29
 * @version: V1.0
 */
package com.leet.code;


import com.common.ListNode;

/**
 * @className: LinkedListAlgorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-14 11:29
 */
public class LinkedListAlgorithm {
    /**
     * 链表反转（递归实现）
     *
     * @param head 1->2->3->4->5->null
     *             1->2<-3<-4<-5
     * @return
     */
    public static ListNode reverse(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode subNode = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return subNode;
    }

    /**
     * 链表反转（迭代实现）
     *
     * @param head 1->2->3->4->5->null
     *             null <-1 <- 2 <- 3 <- 4 <- 5 null
     * @return
     */
    public static ListNode reverse2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * 链表前n个反转（递归实现）
     *
     * @param head 1->2->3->4->5->null
     * 1->3->2->4->5->null
     * @return
     */
    public static ListNode afterNode;

    public static ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            afterNode = head.next;
            return head;
        }
        ListNode subNode = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = afterNode;
        return subNode;
    }

    /**
     * 链表前n个反转（迭代实现）
     *
     * @param head
     * @return null <- 1 <- 2  3 -> 4 -> 5 -> null
     */
    public static ListNode reverseN2(ListNode head, int n) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        int k = 1;
        while (cur != null && k <= n) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            k++;
        }
        head.next = cur;
        return pre;
    }

    /**
     * 链表前m-n个反转（递归实现）
     *
     * @param head
     * @return
     */
    public static ListNode reverseMN(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        head.next = reverseMN(head.next, m - 1, n - 1);
        return head;
    }


    /**
     * 链表前m-n个反转（迭代实现）
     *
     * @param head
     * @return null -> 1 -> 2 <- 3 <- 4  5
     */
    public static ListNode reverseMN2(ListNode head, int m, int n) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        ListNode mNode = null;
        int k = 1;
        while (cur != null && k <= n) {
            next = cur.next;
            if (k == m) {
                mNode = cur;
            }
            if (k >= m) {
                cur.next = pre;
            }
            pre = cur;
            cur = next;
            k++;
        }
        mNode.next.next = pre;
        mNode.next = cur;
        return head;
    }

    /**
     * k个一组反转链表（不足k时不反转）
     *
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        int i = 0;
        ListNode node = head;
        while (i < k) {
            if (node == null) {
                return head;
            }
            i++;
            node = node.next;
        }

        ListNode pre = head;
        ListNode newNode = reverseN2(head, k);
        pre.next = reverseKGroup(node, k);
        return newNode;
    }

    static ListNode left = null;

    /**
     * 判断回文单链表
     * 时间复杂度：O(n) 空间复杂度：O(n)
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    public static boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean result = traverse(right.next);
        result = result && (right.val == left.val);
        left = left.next;
        return result;
    }

    /**
     * 判断回文单链表
     * 时间复杂度：O(n) 空间复杂度：O(1)
     *
     * @param head
     * @return 1 2 2 1
     * 1 2 3 2 1
     */
    public static boolean isPalindrome2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }
        ListNode lNode = head;

        ListNode rNode = reverse2(slow);

        while (lNode != null && rNode != null) {
            if (lNode.val != rNode.val) return false;
            lNode = lNode.next;
            rNode = rNode.next;
        }
        return true;
    }

    /**
     * 链表交叉节点
     *
     * @param a
     * @param b
     * @return
     */
    public static ListNode intersectionNode(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return null;
        }
        ListNode aNode = a;
        ListNode bNode = b;
        int i = 1;
        while (aNode != null) {
            i++;
            aNode = aNode.next;
        }
        int j = 1;
        while (bNode != null) {
            j++;
            bNode = bNode.next;
        }
        if (i > j) {
            int k = i - j;
            while (k > 0) {
                a = a.next;
                k--;
            }
        } else if (i < j) {
            int k = j - i;
            while (k > 0) {
                b = b.next;
                k--;
            }
        }

        while (a != null && b != null) {
            if (a.val == b.val) {
                return a;
            }
            a = a.next;
            b = b.next;
        }

        return null;
    }


}
