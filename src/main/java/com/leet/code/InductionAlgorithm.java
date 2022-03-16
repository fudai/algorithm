/**
 * Copyright (c) 2009-2020 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: InductionAlgorithm
 * @package: com.leet.code
 * @date: 2020-03-10 16:05
 * @version: V1.0
 */
package com.leet.code;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @className: InductionAlgorithm
 * @description: 入门算法
 * @author: fudai
 * @date: 2020-03-10 16:05
 */
public class InductionAlgorithm {

    /**
     * 合并有序链表（非递归）
     *
     * @param aNode a链表
     * @param bNode b链表
     * @return 合并链表
     */
    public static LinkNode<Integer> mergeOrderLinkedListA(LinkNode<Integer> aNode, LinkNode<Integer> bNode) {
        LinkNode<Integer> tempNode = new LinkNode<Integer>();
        LinkNode<Integer> mergeNode = tempNode;

        while (aNode != null || bNode != null) {
            if (aNode == null) {
                tempNode.next = bNode;
                tempNode = tempNode.next;
                bNode = bNode.next;
                continue;
            }

            if (bNode == null) {
                tempNode.next = aNode;
                tempNode = tempNode.next;
                aNode = aNode.next;
                continue;
            }

            if (aNode.value >= bNode.value) {
                tempNode.next = bNode;
                bNode = bNode.next;
            } else {
                tempNode.next = aNode;
                aNode = aNode.next;
            }
            tempNode = tempNode.next;
        }
        return mergeNode.next;
    }


    /**
     * 合并有序链表（递归）
     *
     * @param aNode a链表
     * @param bNode b链表
     * @return 合并链表
     */
    public static LinkNode<Integer> mergeOrderLinkedListB(LinkNode<Integer> aNode, LinkNode<Integer> bNode) {
        LinkNode<Integer> mergeNode = null;
        if (aNode != null && bNode != null) {
            if (aNode.value >= bNode.value) {
                mergeNode = bNode;
                mergeNode.next = mergeOrderLinkedListB(aNode, bNode.next);
            } else {
                mergeNode = aNode;
                mergeNode.next = mergeOrderLinkedListB(aNode.next, bNode);
            }
        }

        if (aNode == null) {
            mergeNode = bNode;
        }

        if (bNode == null) {
            mergeNode = aNode;
        }
        return mergeNode;
    }

    /**
     * 合并多个有序链表（递归）
     *
     * @param linkNodes 多个链表
     * @return 排序链表
     */
    public static LinkNode<Integer> mergeMoreOrderLinkedList(List<LinkNode<Integer>> linkNodes) {
        LinkNode<Integer> mergeNode = null;
        for (int i = 0; i < linkNodes.size(); i++) {
            if (linkNodes.get(i) == null) {
                linkNodes.remove(i);
            }
        }

        if (linkNodes.size() == 1) {
            mergeNode = linkNodes.get(0);
            return mergeNode;
        }

        if (linkNodes.isEmpty()) {
            mergeNode = null;
            return mergeNode;
        }

        Integer min = linkNodes.get(0).value;
        int minKey = 0;
        for (int i = 0; i < linkNodes.size(); i++) {
            if (linkNodes.get(i) != null && linkNodes.get(i).value < min) {
                min = linkNodes.get(i).value;
                minKey = i;
            }
        }

        mergeNode = linkNodes.get(minKey);
        LinkNode minLinkNode = linkNodes.get(minKey);
        minLinkNode = minLinkNode.next;
        if (minLinkNode == null) {
            linkNodes.remove(minKey);
        } else {
            linkNodes.set(minKey, minLinkNode);
        }
        mergeNode.next = mergeMoreOrderLinkedList(linkNodes);

        return mergeNode;
    }

    /**
     * 括号生成
     *
     * @param result 生成结果
     * @param str    输入字符串
     * @param left   左括号数量
     * @param right  右括号数量
     */
    public static void genBrackets(List<String> result, String str, int left, int right) {
        if (right == 0) {
            result.add(str);
            return;
        }
        if (left > 0) {
            str = str + "(";
            genBrackets(result, str, left - 1, right);
        }
        if (left < right) {
            str = str + ")";
            genBrackets(result, str, left, right - 1);
        }
    }

    /**
     * 交换链表相邻节点
     *
     * @param linkNode 原始链表
     * @return 交互后链表
     */
    public static LinkNode<Integer> swapNode(LinkNode<Integer> linkNode) {
        LinkNode<Integer> swapNode = null;
        if (linkNode != null && linkNode.next == null) {
            swapNode = linkNode;
        }

        if (linkNode != null && linkNode.next != null) {
            LinkNode<Integer> nextNode = linkNode.next;
            LinkNode<Integer> nextNextNode = nextNode.next;
            linkNode.next = swapNode(nextNextNode);
            nextNode.next = linkNode;
            swapNode = nextNode;
        }
        return swapNode;
    }

    /**
     * k个节点翻转
     *
     * @param linkNode 原始链表
     * @return 翻转后链表
     */
    public static LinkNode<Integer> swapKNode(LinkNode<Integer> linkNode, int k) {
        LinkNode<Integer> swapTempNode = null;
        LinkNode<Integer> swapFirstNode = null;

        if (linkNode == null) {
            return null;
        }

        Stack<LinkNode<Integer>> stack = new Stack<LinkNode<Integer>>();
        int i = 0;
        while (i < k && linkNode != null) {
            stack.push(linkNode);
            i++;
            linkNode = linkNode.next;
        }
        while (!stack.empty()) {
            LinkNode<Integer> tempNode = stack.pop();
            tempNode.next = null;
            if (swapTempNode != null) {
                swapTempNode.next = tempNode;
                swapTempNode = swapTempNode.next;
            } else {
                swapFirstNode = tempNode;
                swapTempNode = swapFirstNode;
            }
        }
        if (linkNode != null && swapTempNode != null) {
            swapTempNode.next = swapKNode(linkNode, k);
        }
        return swapFirstNode;
    }

    /**
     * 删除排序数组中的重复项
     *
     * @param array 排序数据
     * @return 不重复元素个数
     */
    public static int removeSortRepeatArray(int[] array) {
        int i = 0;
        int length = array.length;
        while (i < length) {
            int key = array[i];

            int j = 0;
            while ((i + j + 1) < length && array[i + j + 1] == key) {
                j++;
            }
            int num = j;
            int keyNum = i;
            int move = 1;
            while (j > 0 && keyNum + num + move < length) {
                array[keyNum + move] = array[keyNum + num + move];
                move++;
            }
            i++;
            length = length - num;
        }
        return length;
    }

    /**
     * 去除数组中的指定重复元素
     *
     * @param array  无序数组
     * @param remove 指定元素
     * @return 去除后的长度
     */
    public static int removeArray(int[] array, int remove) {
        int length = array.length;
        int i = 0;
        while (i < length) {
            if (remove == array[i]) {
                int move = 0;
                int ik = i;
                while (ik + move + 1 < length) {
                    array[ik + move] = array[ik + move + 1];
                    move++;
                }
                length--;
            } else {
                i++;
            }
        }
        return length;
    }

    /**
     * 查询子串在原串中的第一次出现的位置
     *
     * @param str     原串
     * @param findStr 子串
     * @return 第一次出现的位置
     */
    public static int findStrStr(String str, String findStr) {
        char[] array = str.toCharArray();
        char[] findArray = findStr.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int keyi = i;
            int matchNum = 0;
            for (int j = 0; j < findArray.length; j++) {
                if (findArray[j] != array[keyi]) {
                    break;
                }
                keyi++;
                matchNum++;
            }
            if (matchNum == findArray.length) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 不使用除法计算商
     *
     * @param divided 被除数
     * @param divisor 除数
     * @return 商
     */
    public static int divide(int divided, int divisor) {
        int result = 0;
        boolean flag = (divided > 0 && divisor > 0) || (divided < 0 && divisor < 0);
        int dividedAbs = Math.abs(divided);
        int divisorAbs = Math.abs(divisor);
        int temp = divisorAbs;
        while (dividedAbs - temp >= 0) {
            result++;
            temp = temp + divisorAbs;
        }
        return flag ? result : -result;
    }

    /**
     * 查询混合字串数组的位置
     *
     * @param str         原始串
     * @param subStrArray 字串数组
     * @return
     */
    public static List findStrStrMix(String str, String[] subStrArray) {
        List result = new ArrayList();
        char[] array = str.toCharArray();
        int length = 0;
        for (int i = 0; i < subStrArray.length; i++) {
            length = length + subStrArray[i].length();
        }

        for (int i = 0; i < array.length - length; i++) {
            String subStr = str.substring(i, i + length);
            boolean hit = true;
            for (int j = 0; j < subStrArray.length; j++) {
                if (!subStr.contains(subStrArray[j])) {
                    hit = false;
                    break;
                }
            }
            if (hit) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 查找当前排序的后一个大排序（如果原数组最大，返回最小数据排列）
     *
     * @param array 原数组
     * @return 大于原数据的最小的排序
     */
    public static int[] getNextArrange(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] > array[i - 1]) {
                int temp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = temp;
                return array;
            }
        }

        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
        return array;
    }

    public static String longestValidBracket(String str) {
        char[] array = str.toCharArray();
        Stack<Character> stack = new Stack();
        int i = 0;
        String result = "";
        while (i < array.length) {
            if (array[i] == '(') {
                stack.push('(');
                i++;
            } else {
                int j = i;
                int num = 0;
                while (j < array.length && array[j] != '(') {
                    num++;
                    j++;
                }
                int valide = 0;
                for (int k = 0; k < num; k++) {
                    if (!stack.empty()) {
                        valide++;
                        result = result + stack.pop();
                    }
                }
                for (int k = 0; k < valide; k++) {
                    result = result + ")";
                }
                i = i + num;
            }
        }
        return result;

    }

    public static void main(String[] args) {
//        mergeOrderLinkedListTest();
//        genBracketsTest();
//        mergeMoreOrderLinkedListTest();
//        swapNodeTest();
//        swapKNodeTest();
//        removeSortRepeatArrayTest();
//        removeArrayTest();
//        System.out.println(findStrStr("12345678", "2"));
//        System.out.println(divide(12,-3));
//        System.out.println(findStrStrMix("qqqwwweeerrrtttqqqwwwqqqeeeffffggg", new String[]{"qqq", "www", "eee"}));
//        getNextArrangeTest();
        String str = "(((((((((()()(()))))";
        System.out.println(longestValidBracket(str));
    }

    private static void getNextArrangeTest() {
        int[] array = getNextArrange(new int[]{3, 2, 2, 1, 1});
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void removeArrayTest() {
        int[] array = new int[]{1, 1, 1, 1, 11, 2, 3, 4, 4, 4, 11, 4, 4, 4, 4, 4, 4, 5, 6, 11, 7, 8, 8, 8, 8, 8};
        int length = removeArray(array, 12);
        System.out.println("length:" + length);
        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void removeSortRepeatArrayTest() {
        int[] array = new int[]{1, 1, 1, 1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 6, 7, 8, 8, 8, 8, 8};
        int length = removeSortRepeatArray(array);
        System.out.println("length:" + length);
        for (int i = 0; i < length; i++) {
            System.out.println(array[i]);
        }
    }

    private static void swapKNodeTest() {
        LinkNode<Integer> a =
//                null;
                new LinkNode<Integer>(1,
                        new LinkNode<Integer>(4, new LinkNode<Integer>(7,
                                new LinkNode<Integer>(10, null))));
        LinkNode<Integer> swapNode = swapKNode(a, 1);
        while (swapNode != null) {
            System.out.println(swapNode.value);
            swapNode = swapNode.next;
        }
    }

    private static void swapNodeTest() {
        LinkNode<Integer> a =
//                null;
                new LinkNode<Integer>(1,
                        new LinkNode<Integer>(4, new LinkNode<Integer>(7,
                                new LinkNode<Integer>(10, null))));
        LinkNode<Integer> swapNode = swapNode(a);
        while (swapNode != null) {
            System.out.println(swapNode.value);
            swapNode = swapNode.next;
        }
    }

    private static void mergeMoreOrderLinkedListTest() {
        LinkNode<Integer> a =
//                null;
                new LinkNode<Integer>(1,
                        new LinkNode<Integer>(4, new LinkNode<Integer>(7,
                                new LinkNode<Integer>(10, new LinkNode<Integer>(13, null)))));

        LinkNode<Integer> b =
//                null;
                new LinkNode<Integer>(2,
                        new LinkNode<Integer>(5, new LinkNode<Integer>(8,
                                new LinkNode<Integer>(11, new LinkNode<Integer>(14, null)))));

        LinkNode<Integer> c =
//                null;
                new LinkNode<Integer>(3,
                        new LinkNode<Integer>(6, new LinkNode<Integer>(9,
                                new LinkNode<Integer>(12, new LinkNode<Integer>(15, null)))));

        List<LinkNode<Integer>> linkNodes = new ArrayList<LinkNode<Integer>>();
        linkNodes.add(a);
        linkNodes.add(b);
        linkNodes.add(c);
        LinkNode<Integer> mergeNodes = mergeMoreOrderLinkedList(linkNodes);

        while (mergeNodes != null) {
            System.out.println(mergeNodes.value);
            mergeNodes = mergeNodes.next;
        }
    }

    private static void genBracketsTest() {
        List<String> result = new ArrayList<String>();
        genBrackets(result, "", 3, 3);
        System.out.println(result);
    }

    private static void mergeOrderLinkedListTest() {
        LinkNode<Integer> a =
//                null;
                new LinkNode<Integer>(1,
                        new LinkNode<Integer>(3, new LinkNode<Integer>(5,
                                new LinkNode<Integer>(7, new LinkNode<Integer>(9, null)))));


        LinkNode<Integer> b =
//                null;
                new LinkNode<Integer>(2,
                        new LinkNode<Integer>(4, new LinkNode<Integer>(6,
                                new LinkNode<Integer>(8, new LinkNode<Integer>(10, null)))));

//        LinkNode<Integer> ab = mergeOrderLinkedListA(a, b);
        LinkNode<Integer> ab = mergeOrderLinkedListB(a, b);

        while (ab != null) {
            System.out.println(ab.value);
            ab = ab.next;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LinkNode<T> {
        T value;
        LinkNode<T> next;
    }

}
