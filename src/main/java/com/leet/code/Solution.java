/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: Solution
 * @package: com.leet.code
 * @date: 2021-12-06 13:53
 * @version: V1.0
 */
package com.leet.code;

import com.common.ListNode;
import com.common.TreeNode;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @className: Solution
 * @description:
 * @author: fudai
 * @date: 2021-12-06 13:53
 */
public class Solution {

    /**
     * 两数之和
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode merge = null;
        ListNode temp = null;
        int add = 0;
        while (l1 != null && l2 != null) {
            int c = l1.val + l2.val + add;
            int c1 = c % 10;
            if (merge == null) {
                temp = new ListNode(c1);
                merge = temp;
            } else {
                temp.next = new ListNode(c1);
                temp = temp.next;
            }
            add = c / 10;
            l1 = l1.next;
            l2 = l2.next;
        }
        //l1有剩余
        while (l1 != null) {
            int c = l1.val + add;
            int c1 = c % 10;
            temp.next = new ListNode(c1);
            temp = temp.next;
            l1 = l1.next;
            add = c / 10;
        }
        //l2有剩余
        while (l2 != null) {
            int c = l2.val + add;
            int c1 = c % 10;
            temp.next = new ListNode(c1);
            temp = temp.next;
            l2 = l2.next;
            add = c / 10;
        }
        //最后有进位
        if (add != 0) {
            temp.next = new ListNode(add);
        }
        return merge;
    }

    /**
     * 寻找两个正序数组的中位数
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0;
        int p = (nums1.length + nums2.length) / 2 - 1;
        int ml = 0, mr = 0;
        while (i < nums1.length || j < nums2.length) {
            if (j >= nums2.length || (i < nums1.length && j < nums2.length && nums1[i] < nums2[j])) {
                if (k == p) {
                    ml = nums1[i];
                }
                if (k == p + 1) {
                    mr = nums1[i];
                    break;
                }
                i++;
            } else {
                if (k == p) {
                    ml = nums2[j];
                }
                if (k == p + 1) {
                    mr = nums2[j];
                    break;
                }
                j++;
            }
            k++;
        }
        if ((nums1.length + nums2.length) % 2 == 1) {
            return mr;
        } else {
            return (ml + mr) / 2.0;
        }
    }

    /**
     * Z 字形变换
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        StringBuilder[] stringBuilders = new StringBuilder[numRows];
        for (int i = 0; i < stringBuilders.length; i++) {
            stringBuilders[i] = new StringBuilder("");
        }
        for (int i = 0; i < chars.length; i++) {
            int key = i % (2 * numRows - 2);
            if (key >= numRows) {
                stringBuilders[2 * numRows - key - 2].append(chars[i]);
            } else {
                stringBuilders[key].append(chars[i]);
            }
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : stringBuilders) {
            result.append(sb);
        }
        return result.toString();
    }

    /**
     * 整数反转
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result < Integer.MIN_VALUE / 10 || result > Integer.MAX_VALUE / 10) {
                return 0;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result;
    }

    /**
     * 字符串转换整数
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        boolean flag = true;
        int num = 0;
        char[] chars = s.toCharArray();
        long result = 0;
        int noBlank = 0;
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                result = result * 10 + c - '0';
                noBlank++;
            } else if (c == ' ' && noBlank == 0) {
                continue;
            } else if (c == '+' && num == 0 && noBlank == 0) {
                flag = true;
                num++;
                noBlank++;
            } else if (c == '-' && num == 0 && noBlank == 0) {
                if (noBlank > 1) {
                    return 0;
                }
                flag = false;
                num++;
                noBlank++;
            } else {
                break;
            }
            if (flag && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (!flag && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        result = flag ? result : -result;
        return (int) (result);
    }


    public static Map<String, List<String>> stateMachine = new HashMap<>();

    static {
        List<String> start = new ArrayList<>();
        start.add("start");
        start.add("sign");
        start.add("num");
        start.add("end");
        stateMachine.put("start", start);

        List<String> sign = new ArrayList<>();
        sign.add("end");
        sign.add("end");
        sign.add("num");
        sign.add("end");
        stateMachine.put("sign", sign);

        List<String> num = new ArrayList<>();
        num.add("end");
        num.add("end");
        num.add("num");
        num.add("end");
        stateMachine.put("num", num);

        List<String> other = new ArrayList<>();
        other.add("end");
        other.add("end");
        other.add("end");
        other.add("end");
        stateMachine.put("end", other);

    }

    public int getCharType(char c) {
        if (c >= '0' && c <= '9') {
            return 2;
        } else if (c == ' ') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else {
            return 3;
        }
    }

    /**
     * 字符串转换整数（状态机）
     *
     * @param s
     * @return
     */
    public int myAtoi2(String s) {
        char[] chars = s.toCharArray();
        boolean flag = true;
        String state = "start";
        long result = 0;
        for (char c : chars) {
            String type = stateMachine.get(state).get(getCharType(c));
            if ("sign".equals(type)) {
                if (c == '+') {
                    flag = true;
                } else if (c == '-') {
                    flag = false;
                }
            }
            if ("num".equals(type)) {
                result = result * 10 + (c - '0');
            }
            if (flag && result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (!flag && -result < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            if ("end".equals(type)) {
                break;
            }
            state = type;
        }
        return (int) (flag ? result : -result);
    }

    /**
     * 是否回文数
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        List<Integer> integerList = new ArrayList<>();
        while (x > 0) {
            int mod = x % 10;
            integerList.add(mod);
            x = x / 10;
        }
        int left = 0;
        int right = integerList.size() - 1;
        while (left <= right) {
            if (integerList.get(left) != integerList.get(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 盛最多水的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        while (left < right) {
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return result;
    }

    /**
     * 整数转罗马数字
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            if (num >= 1000) {
                result.append("M");
                num -= 1000;
            } else if (num >= 900) {
                result.append("CM");
                num -= 900;
            } else if (num >= 500) {
                result.append("D");
                num -= 500;
            } else if (num >= 400) {
                result.append("CD");
                num -= 400;
            } else if (num >= 100) {
                result.append("C");
                num -= 100;
            } else if (num >= 90) {
                result.append("XC");
                num -= 90;
            } else if (num >= 50) {
                result.append("L");
                num -= 50;
            } else if (num >= 40) {
                result.append("XL");
                num -= 40;
            } else if (num >= 10) {
                result.append("X");
                num -= 10;
            } else if (num >= 9) {
                result.append("IX");
                num -= 9;
            } else if (num >= 5) {
                result.append("V");
                num -= 5;
            } else if (num >= 4) {
                result.append("IV");
                num -= 4;
            } else if (num >= 1) {
                result.append("I");
                num -= 1;
            }
        }
        return result.toString();
    }

    /**
     * 罗马数字转整数
     *
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int result = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; ) {
            if (i < chars.length - 1 && convert(chars[i]) < convert(chars[i + 1])) {
                result += convert(chars[i + 1]) - convert(chars[i]);
                i += 2;
            } else {
                result += convert(chars[i]);
                i++;
            }
        }
        return result;
    }

    public int convert(char c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
        }
        return 0;
    }

    /**
     * 最长公共前缀
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < strs.length; i++) {
            length = Math.min(length, strs[i].length());
        }
        int k = 0;
        while (k < length) {
            char v = ' ';
            boolean same = true;
            for (int i = 0; i < strs.length; i++) {
                if (i == 0) {
                    v = strs[i].charAt(k);
                } else {
                    if (v != strs[i].charAt(k)) {
                        same = false;
                    }
                }
            }
            if (!same) {
                break;
            }
            sb.append(v);
            k++;
        }
        return sb.toString();
    }

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] > 0) {
                break;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > 0) {
                    right--;
                } else if (nums[i] + nums[left] + nums[right] == 0) {
                    List<Integer> sub = new ArrayList<>();
                    sub.add(nums[i]);
                    sub.add(nums[left]);
                    sub.add(nums[right]);
                    result.add(sub);
                    while (left + 1 < nums.length && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right - 1 > 0 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 最接近三数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = nums[0] + nums[1] + nums[2];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] > target) {
                    if (nums[i] + nums[left] + nums[right] - target < Math.abs(result - target)) {
                        result = nums[i] + nums[left] + nums[right];
                    }
                    right--;
                } else if (nums[i] + nums[left] + nums[right] == target) {
                    return target;
                } else {
                    if (target - (nums[i] + nums[left] + nums[right]) < Math.abs(result - target)) {
                        result = nums[i] + nums[left] + nums[right];
                    }
                    left++;
                }
            }
        }
        return result;
    }

    /**
     * 电话号码的字母组合
     *
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits == "" || digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        char[] chars = digits.toCharArray();
        List<StringBuilder> all = new ArrayList<>();
        List<String> sub0 = convertNum(chars[0]);
        for (int i = 0; i < sub0.size(); i++) {
            StringBuilder sb = new StringBuilder(sub0.get(i));
            all.add(sb);
        }
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            List<String> sub = convertNum(c);
            List<StringBuilder> list = new ArrayList<>();
            all.forEach(stringBuilder -> {
                sub.forEach(s -> {
                    list.add(new StringBuilder(stringBuilder).append(s));
                });
            });
            all = list;
        }

        List<String> result = new ArrayList<>();
        all.forEach(stringBuilder -> {
            result.add(stringBuilder.toString());
        });
        return result;
    }

    public List<String> convertNum(char c) {
        List<String> result = new ArrayList<>();
        switch (c) {
            case '2':
                result.add("a");
                result.add("b");
                result.add("c");
                break;
            case '3':
                result.add("d");
                result.add("e");
                result.add("f");

                break;
            case '4':
                result.add("g");
                result.add("h");
                result.add("i");

                break;
            case '5':
                result.add("j");
                result.add("k");
                result.add("l");

                break;
            case '6':
                result.add("m");
                result.add("n");
                result.add("o");

                break;
            case '7':
                result.add("p");
                result.add("q");
                result.add("r");
                result.add("s");

                break;
            case '8':
                result.add("t");
                result.add("u");
                result.add("v");
                break;
            case '9':
                result.add("w");
                result.add("x");
                result.add("y");
                result.add("z");
                break;

            default:
        }
        return result;
    }

    /**
     * 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null && n == 1) {
            return null;
        }
        ListNode node1 = head;
        ListNode node2 = head;
        ListNode preNode = null;
        for (int i = 0; i < n; i++) {
            node1 = node1.next;
        }
        while (node1 != null) {
            preNode = node2;
            node2 = node2.next;
            node1 = node1.next;
        }
        if (preNode == null) {
            return head.next;
        }
        preNode.next = node2.next;
        return head;
    }

    /**
     * 有效的括号
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.empty()) {
                return false;
            } else {
                char v = stack.pop();
                if (c == ')' && v != '(') {
                    return false;
                } else if (c == '}' && v != '{') {
                    return false;
                } else if (c == ']' && v != '[') {
                    return false;
                }
            }
        }
        return stack.empty();
    }

    /**
     * 括号生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backUp(result, 0, 0, 0, n, new StringBuilder());
        return result;
    }

    private void backUp(List<String> result, int k, int left, int right, int n, StringBuilder sb) {
        if (sb.length() == 2 * n) {
            result.add(sb.toString());
            return;
        }
        if (left < n && left >= right) {
            sb.append("(");
            backUp(result, k + 1, left + 1, right, n, sb);
            sb.deleteCharAt(k);
        }
        if (right < n && left >= right) {
            sb.append(")");
            backUp(result, k + 1, left, right + 1, n, sb);
            sb.deleteCharAt(k);
        }
    }

    /**
     * 合并2个升序链表
     *
     * @param a
     * @param b
     * @return
     */
    public ListNode merge2Lists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (a != null && b != null) {
            if (a.val > b.val) {
                tail.next = b;
                b = b.next;
            } else {
                tail.next = a;
                a = a.next;
            }
            tail = tail.next;
        }
        if (a != null) {
            tail.next = a;
        }
        if (b != null) {
            tail.next = b;
        }
        return head.next;
    }

    /**
     * 合并K个升序链表 （顺序合并）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode listNode = null;
        ListNode merge = null;
        for (int i = 0; i < lists.length; i++) {
            merge = merge2Lists(merge, lists[i]);
        }
        return merge;
    }

    /**
     * 合并K个升序链表 （分治合并）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = l + (r - l) / 2;
        return merge2Lists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }


    /**
     * 合并K个升序链表 （优先队列）
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists3(ListNode[] lists) {
        PriorityQueue<CompareNode> priorityQueue = new PriorityQueue<>();
        for (ListNode node : lists) {
            while (node != null) {
                priorityQueue.add(new CompareNode(node.val, node));
                node = node.next;
            }
        }
        ListNode head = new ListNode(0);
        ListNode tail = head;
        while (!priorityQueue.isEmpty()) {
            tail.next = priorityQueue.poll().node;
            tail = tail.next;
        }
        return head.next;
    }

    public static class CompareNode implements Comparable<CompareNode> {
        int val;
        ListNode node;

        public CompareNode(int val, ListNode node) {
            this.val = val;
            this.node = node;
        }

        @Override
        public int compareTo(CompareNode o) {
            return val - o.val;
        }
    }

    /**
     * 两两交换链表中的节点
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode tail = head;
        ListNode pre = new ListNode(0);
        pre.next = tail;
        ListNode node = pre;
        while (tail != null && tail.next != null) {
            ListNode nextNext = tail.next.next;
            ListNode temp = tail.next;
            pre.next = temp;
            temp.next = tail;
            tail.next = nextNext;
            pre = tail;
            tail = tail.next;
        }
        return node.next;
    }

    /**
     * K 个一组翻转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        ListNode pre = new ListNode(0);
        pre.next = tail;
        int i = 0;
        ListNode node = pre;
        Stack<ListNode> stack = new Stack<>();
        while (i < k && tail != null) {
            stack.push(tail);
            tail = tail.next;
            i++;
        }
        if (i < k) {
            return head;
        }
        while (!stack.empty()) {
            pre.next = stack.pop();
            pre = pre.next;
        }
        if (tail != null) {
            pre.next = reverseKGroup(tail, k);
        } else {
            pre.next = null;
        }
        return node.next;
    }

    /**
     * 删除有序数组中的重复项
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int l = nums.length;
        int k = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                k++;
                nums[k] = nums[i + 1];
            } else {
                l--;
            }
        }
        return l;
    }

    /**
     * 移除元素
     *
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int l = nums.length;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k] = nums[i];
                k++;
            } else {
                l--;
            }
        }
        return l;
    }

    /**
     * 实现 strStr()
     *
     * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0 || haystack.equals(needle)) {
            return 0;
        }
        char[] charsAll = haystack.toCharArray();
        int left = 0;
        while (left < charsAll.length) {
            if (left + needle.length() > haystack.length()) {
                return -1;
            }
            if (haystack.substring(left, left + needle.length()).equals(needle)) {
                return left;
            }
            left++;
        }
        return -1;
    }

    /**
     * 两数相除
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE) {
                return 1;
            } else {
                return 0;
            }
        }
        if (divisor == 0) {
            return 0;
        }
        boolean flag = true;
        if (dividend > 0 && divisor > 0) {
            dividend = -dividend;
            divisor = -divisor;
        }
        if (dividend > 0 && divisor < 0) {
            dividend = -dividend;
            flag = false;
        }
        if (dividend < 0 && divisor > 0) {
            divisor = -divisor;
            flag = false;
        }
        int result = 0;
        int left = 1;
        int right = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (isMatch(dividend, divisor, mid)) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        if (flag) {
            return result;
        } else {
            return -result;
        }
    }

    /**
     * 是否满足 divisor*result>dividend
     *
     * @param dividend
     * @param divisor
     * @param result
     * @return
     */
    private boolean isMatch(int dividend, int divisor, int result) {
        int r = 0;
        while (result > 0) {
            r += divisor;
            result--;
            if (r < dividend) {
                return false;
            }

        }
        return true;
    }

    /**
     * 串联所有单词的子串
     *
     * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
     *
     * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        int l = 0;
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
            l += words[i].length();
        }
        int left = 0;
        int right = 0;
        while (left <= right && right <= s.length()) {
            if (right - left == l && isMatch(s.substring(left, right), map, words[0].length())) {
                result.add(left);
            }
            if (right - left < l) {
                right++;
            } else {
                left++;
            }
        }
        return result;
    }

    private boolean isMatch(String sub, HashMap<String, Integer> map, int length) {
        HashMap<String, Integer> mapResult = new HashMap<>();
        int begin = 0;
        while (begin < sub.length()) {
            String subS = sub.substring(begin, begin + length);
            mapResult.put(subS, mapResult.getOrDefault(subS, 0) + 1);
            begin = begin + length;
        }
        boolean[] result = new boolean[1];
        result[0] = true;
        map.forEach((s, integer) -> {
            if (!mapResult.containsKey(s)) {
                result[0] = false;
            } else if (!map.containsKey(s)) {
                result[0] = false;
            } else {
                if (!mapResult.get(s).equals(map.get(s))) {
                    result[0] = false;
                }
            }
        });
        return result[0];
    }

    /**
     * 下一个排列
     *
     * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
     *
     * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
     * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
     *
     * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
     * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
     * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
     * 给你一个整数数组 nums ，找出 nums 的下一个排列。
     *
     * 必须 原地 修改，只允许使用额外常数空间。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int k = nums.length - 2;
        while (k >= 0) {
            if (nums[k] < nums[k + 1]) {
                break;
            } else {
                k--;
            }
        }
        int left = 0;
        int right = nums.length - 1;
        if (k >= 0) {
            int i = nums.length - 1;
            while (i > k) {
                if (nums[i] > nums[k]) {
                    break;
                } else {
                    i--;
                }
            }
            int temp = nums[k];
            nums[k] = nums[i];
            nums[i] = temp;
            left = k + 1;
            right = nums.length - 1;
        }

        while (left < right) {
            int temp2 = nums[left];
            nums[left] = nums[right];
            nums[right] = temp2;
            left++;
            right--;
        }

    }

    /**
     * 最长有效括号
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int result = 0;
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                if (!stack.empty()) {
                    stack.pop();
                    if (stack.empty()) {
                        result = Math.max(result, i - start + 1);
                    } else {
                        result = Math.max(result, i - stack.peek());
                    }
                } else {
                    start = i + 1;
                }
            } else {
                stack.push(i);
            }
        }
        return result;
    }

    /**
     * 搜索旋转排序数组
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target < nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 搜索插入位置
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                if (target == nums[i]) {
                    return i;
                } else if (target > nums[i]) {
                    return i + 1;
                }
            } else {

                if (target == nums[i]) {
                    return i;
                } else if (target > nums[i] && target < nums[i + 1]) {
                    return i + 1;
                }
            }
        }
        return 0;
    }

    /**
     * 有效的数独
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.' && !valid(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean valid(char[][] board, int i, int j) {
        for (int m = 0; m < board[i].length; m++) {
            if (m != j && board[i][m] == board[i][j]) {
                return false;
            }
        }

        for (int n = 0; n < board.length; n++) {
            if (n != i && board[n][j] == board[i][j]) {
                return false;
            }
        }

        int l = i / 3;
        int r = j / 3;

        for (int m = l * 3; m < (l + 1) * 3; m++) {
            for (int n = r * 3; n < (r + 1) * 3; n++) {
                if (m != i && n != j && board[m][n] == board[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    //todo

    /**
     * 解数独
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {

    }

    /**
     * 外观数列
     *
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        int i = 0;
        String s = "1";
        while (i < n - 1) {
            s = countAndSaySub(s);
            i++;
        }
        return s;
    }

    private String countAndSaySub(String v) {
        StringBuilder sb = new StringBuilder();
        char[] chars = v.toCharArray();
        char k = chars[0];
        int num = 0;
        for (int i = 0; i < chars.length; i++) {
            if (k == chars[i]) {
                num = num + 1;
            } else {
                sb = sb.append(num).append(k);
                k = chars[i];
                num = 1;
            }
        }
        sb = sb.append(num).append(k);
        return sb.toString();
    }

    /**
     * 组合总和
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        combinationSumSub(result, combine, candidates, 0, target);
        return result;
    }

    private void combinationSumSub(List<List<Integer>> result, List<Integer> combine, int[] candidates, int i, int target) {
        if (i == candidates.length) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(combine));
            return;
        }
        //不使用 i
        combinationSumSub(result, combine, candidates, i + 1, target);

        //使用 i
        if (target - candidates[i] >= 0) {
            combine.add(candidates[i]);
            combinationSumSub(result, combine, candidates, i, target - candidates[i]);
            combine.remove(combine.size() - 1);
        }
    }


    Map<Integer, Integer> map = new HashMap<>();

    /**
     * 组合总和 II
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        for (int i = 0; i < candidates.length; i++) {
            map.put(candidates[i], map.getOrDefault(candidates[i], 0) + 1);
        }
        int[] arr = new int[map.size()];
        int i = 0;
        for (int k : map.keySet()) {
            arr[i] = k;
            i++;
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combine = new ArrayList<>();
        combinationSum2Sub(result, combine, arr, 0, target);
        return result;
    }

    private void combinationSum2Sub(List<List<Integer>> result, List<Integer> combine, int[] arr, int i, int target) {
        if (target == 0) {
            result.add(new ArrayList<>(combine));
            return;
        }
        if (i == arr.length) {
            return;
        }
        //不使用 i
        combinationSum2Sub(result, combine, arr, i + 1, target);

        //使用 i
        int num = map.get(arr[i]);
        for (int j = 1; j <= num; j++) {
            if (target - arr[i] * j >= 0) {
                for (int k = 0; k < j; k++) {
                    combine.add(arr[i]);
                }
                combinationSum2Sub(result, combine, arr, i + 1, target - arr[i] * j);
                for (int k = 0; k < j; k++) {
                    combine.remove(combine.size() - 1);
                }
            }
        }

    }

    /**
     * 缺失的第一个正数（哈希表）
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums.length || nums[i] <= 0) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int k = Math.abs(nums[i]);
            if (k <= nums.length) {
                nums[k - 1] = -Math.abs(nums[k - 1]);
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] >= 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * 缺失的第一个正数（置换）
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while (nums[i] >= 1 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * 接雨水
     *
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        rightMax[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        for (int j = height.length - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return result;
    }

    /**
     * 字符串相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        char[] num1C = num1.toCharArray();
        char[] num2C = num2.toCharArray();
        String s = "0";
        for (int i = num1C.length - 1; i >= 0; i--) {
            StringBuilder result = new StringBuilder();
            int k = num1C.length - 1 - i;
            while (k > 0) {
                result.append(0);
                k--;
            }
            int x = num1C[i] - '0';
            int add = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int y = num2C[j] - '0';
                int z = x * y + add;
                result.append(z % 10);
                add = z / 10;
            }
            if (add > 0) {
                result.append(add);
            }
            s = addString(s, result.reverse().toString());
        }
        return s;
    }

    private String addString(String s1, String s2) {
        char[] s1C = s1.toCharArray();
        char[] s2C = s2.toCharArray();
        int i = 0;
        StringBuilder result = new StringBuilder();
        int add = 0;
        while (i < s1.length() || i < s2.length()) {
            int x = 0;
            int y = 0;
            if (s1C.length - 1 - i >= 0) {
                x = s1C[s1C.length - 1 - i] - '0';
            }
            if (s2C.length - 1 - i >= 0) {
                y = s2C[s2C.length - 1 - i] - '0';
            }
            int z = x + y + add;
            result.append(z % 10);
            add = z / 10;
            i++;
        }
        if (add > 0) {
            result.append(add);
        }
        return result.reverse().toString();
    }

    /**
     * 通配符匹配 （动态规划）
     *
     * @param s
     * @param p dp[i][j]
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {//两种情况：1.使用*号 2.不使用*号
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1));
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    /**
     * 通配符匹配 （贪心算法）
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch2(String s, String p) {
        return true;
    }

    /**
     * 跳跃游戏 II
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int i = 0;
        int k = 0;
        int max = 0;
        int end = 0;
        while (i < nums.length - 1) {
            max = Math.max(max, nums[i] + i);
            if (i == end) {
                k++;
                end = max;
            }
            i++;
        }
        return k;
    }

    /**
     * 全排列
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] use = new boolean[nums.length];
        backUp(result, nums, 0, new ArrayList<Integer>(), use);
        return result;
    }

    public void backUp(List<List<Integer>> result, int[] nums, int k, List<Integer> sub, boolean[] use) {
        if (nums.length == k) {
            result.add(new CopyOnWriteArrayList<>(sub));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!use[i]) {
                sub.add(nums[i]);
                use[i] = true;
                backUp(result, nums, k + 1, sub, use);
                use[i] = false;
                sub.remove(sub.size() - 1);
            }
        }

    }

    /**
     * 全排列  II  （不重复）
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] use = new boolean[nums.length];
        Arrays.sort(nums);
        backUp2(result, nums, 0, new ArrayList<Integer>(), use);
        return result;
    }

    public void backUp2(List<List<Integer>> result, int[] nums, int k, List<Integer> sub, boolean[] use) {
        if (nums.length == k) {
            result.add(new CopyOnWriteArrayList<>(sub));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !use[i - 1]) {
                continue;
            }
            if (!use[i]) {
                sub.add(nums[i]);
                use[i] = true;
                backUp2(result, nums, k + 1, sub, use);
                use[i] = false;
                sub.remove(sub.size() - 1);
            }
        }
    }

    /**
     * 旋转图像 （直接旋转）
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        /*
         * 00 01 02 03
         * 10 11 12 13
         * 20 21 22 23
         * 30 31 32 33
         *
         * 30 20 10 00
         * 31 21 11 01
         * 32 22 12 02
         * 33 23 13 03
         *
         *
         * 1 2 3
         * 4 5 6
         * 7 8 9
         *
         * 7 4 1
         * 8 5 2
         * 9 6 3
         *
         * matrix[j][n-i-1] = matrix[i][j]
         * matrix[n-i-1][n-j-1] = matrix[j][n-i-1]
         * matrix[n-j-1][i] = matrix[n-i-1][n-j-1]
         * matrix[i][j] = matrix[n-j-1][i]
         */
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    /**
     * 旋转图像 （先上下对称，后对角线对称）
     *
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        /*
         * 00 01 02 03
         * 10 11 12 13
         * 20 21 22 23
         * 30 31 32 33
         *
         * 30 31 32 33
         * 20 21 22 23
         * 10 11 12 13
         * 00 01 02 03
         *
         * 30 20 10 00
         * 31 21 11 01
         * 32 22 12 02
         * 33 23 13 03
         *
         * matrix[i][j] = matrix[j][n-i-1]
         * matrix[j][n-i-1] = matrix[n-i-1][n-j-1]
         * matrix[n-i-1][n-j-1] = matrix[n-j-1][i]
         * matrix[n-j-1][i] = matrix[i][j]
         */
        int n = matrix.length;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 字母异位词分组
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> result = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = new String(chars);
            if (result.containsKey(sortStr)) {
                result.get(sortStr).add(str);
            } else {
                List<String> sub = new ArrayList<>();
                sub.add(str);
                result.put(sortStr, sub);
            }
        }
        return new ArrayList<>(result.values());
    }

    /**
     * Pow(x, n)
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n == 0 || x == 1) {
            return 1;
        }
        if (x == -1) {
            if (n % 2 == 0) {
                return 1;
            } else {
                return -1;
            }
        }
        if (n <= Integer.MIN_VALUE) {
            return 0;
        }
        double result = 1;
        if (n > 0) {
            while (n > 0) {
                result = result * x;
                n--;
            }
        } else {
            n = -n;
            while (n > 0) {
                result = result * x;
                n--;
            }
            result = 1.0 / result;
        }
        return result;
    }

    /**
     * Pow(x, n)  快速幂 + 递归
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n) {
        return n > 0 ? quickPow(x, n) : 1.0 / quickPow(x, -n);
    }

    public double quickPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double temp = quickPow(x, n / 2);
        if (n % 2 == 0) {
            return temp * temp;
        } else {
            return temp * temp * x;
        }
    }

    int num = 0;

    /**
     * N皇后 II
     *
     * @param n
     * @return
     */
    public int totalNQueens(int n) {
        boolean[][] arr = new boolean[n][n];
        nQueens(arr, 0);
        return num;
    }

    private void nQueens(boolean[][] arr, int k) {
        if (k >= arr.length) {
            num++;
            return;
        }
        for (int j = 0; j < arr.length; j++) {
            if (!canFight(arr, k, j)) {
                arr[k][j] = true;
                nQueens(arr, k + 1);
                arr[k][j] = false;
            }
        }
    }


    private boolean canFight(boolean[][] arr, int i, int j) {
        //同一列
        for (int m = 0; m < i; m++) {
            if (arr[m][j] == true) {
                return true;
            }
        }
        //同一行
        for (int n = 0; n < j; n++) {
            if (arr[i][n] == true) {
                return true;
            }
        }
        //左上方
        for (int m = i - 1, n = j - 1; m >= 0 && n >= 0; n--, m--) {
            if (arr[m][n] == true) {
                return true;
            }
        }
        //右上方
        for (int m = i - 1, n = j + 1; m >= 0 && n < arr.length; m--, n++) {
            if (arr[m][n] == true) {
                return true;
            }
        }
        return false;
    }

    /**
     * 最大子数组和
     *
     * @param nums dp[i] 以nums[i]为结尾的最大子数组和
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 最大子数组和优化
     *
     * @param nums dp[i] 以nums[i]为结尾的最大子数组和
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int pre = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }

    /**
     * 螺旋矩阵
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int down = matrix.length - 1;
        while (left <= right && top <= down) {
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            for (int i = top + 1; i < down; i++) {
                result.add(matrix[i][right]);
            }
            for (int j = right; j >= left && top != down; j--) {
                result.add(matrix[down][j]);
            }
            for (int i = down - 1; i > top && left != right; i--) {
                result.add(matrix[i][left]);
            }
            left++;
            right--;
            top++;
            down--;
        }

        return result;
    }

    /**
     * 插入区间
     *
     * @param intervals
     * @param newInterval
     * @return
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int addL = newInterval[0];
        int addR = newInterval[1];
        List<int[]> result = new ArrayList<>();
        boolean tag = false;
        for (int i = 0; i < intervals.length; i++) {
            if (addL > intervals[i][1]) {
                result.add(new int[]{intervals[i][0], intervals[i][1]});
            } else if (addR < intervals[i][0]) {
                if (!tag) {
                    result.add(new int[]{addL, addR});
                    tag = true;
                }
                result.add(new int[]{intervals[i][0], intervals[i][1]});
            } else {
                addL = Math.min(addL, intervals[i][0]);
                addR = Math.max(addR, intervals[i][1]);
            }
        }
        if (!tag) {
            result.add(new int[]{addL, addR});
        }
        int[][] s = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            s[i][0] = result.get(i)[0];
            s[i][1] = result.get(i)[1];
        }
        return s;
    }

    /**
     * 最后一个单词的长度
     *
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        int num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                num++;
            } else {
                if (num == 0) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return num;
    }

    /**
     * 螺旋矩阵 II
     *
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int down = n - 1;
        int k = 1;
        while (left <= right && top <= down) {
            for (int j = left; j <= right; j++) {
                matrix[top][j] = k;
                k++;
            }
            for (int i = top + 1; i < down; i++) {
                matrix[i][right] = k;
                k++;
            }
            for (int j = right; j >= left && top != down; j--) {
                matrix[down][j] = k;
                k++;
            }
            for (int i = down - 1; i > top && left != right; i--) {
                matrix[i][left] = k;
                k++;
            }
            left++;
            right--;
            top++;
            down--;
        }
        return matrix;
    }

    /**
     * 旋转链表
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }
        ListNode temp = head;
        ListNode temp2 = head;
        ListNode node = null;
        while (k > 0) {
            if (temp.next == null) {
                temp = head;
            } else {
                temp = temp.next;
            }
            k--;
            if (k == 1 && temp.next == null) {
                return head;
            }
        }

        while (temp.next != null) {
            temp = temp.next;
            temp2 = temp2.next;
        }

        temp.next = head;

        node = temp2.next;

        temp2.next = null;

        return node;

    }


    /**
     * 不同路径
     * dp[i][j] = dp[i-1][j]+dp[i][j-1]
     * base: dp[i][0] = 1,dp[0][j] = 1
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];

    }

    /**
     * 不同路径 II
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            } else {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = 0;
                } else if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] != 1) {
                    dp[i][j] = dp[i][j - 1];
                } else if (obstacleGrid[i - 1][j] != 1 && obstacleGrid[i][j - 1] == 1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];

    }

    /**
     * 最小路径和
     * dp[i][j] = min(dp[i-1][j],dp[i][j-1])
     * base: dp[i][0] = ,dp[0][j] =
     * * @param grid
     *
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int isum = 0;
        for (int i = 0; i < m; i++) {
            isum += grid[i][0];
            dp[i][0] = isum;
        }
        int jsum = 0;
        for (int j = 0; j < n; j++) {
            jsum += grid[0][j];
            dp[0][j] = jsum;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 加一
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int add = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int v = digits[i] + add;
            if (i == digits.length - 1) {
                v++;
            }
            if (v >= 10) {
                digits[i] = v % 10;
                add = v / 10;
            } else {
                digits[i] = v;
                add = 0;
            }
        }
        if (add > 0) {
            int[] r = new int[digits.length + 1];
            r[0] = add;
            for (int i = 1; i < r.length; i++) {
                r[i] = digits[i - 1];
            }
            return r;
        } else {
            return digits;
        }

    }

    /**
     * 二进制求和
     *
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        char[] arra = a.toCharArray();
        char[] arrb = b.toCharArray();
        int i = arra.length - 1;
        int j = arrb.length - 1;
        int add = 0;
        StringBuilder s = new StringBuilder();
        while (i >= 0 && j >= 0) {
            int t = arra[i] - '0' + arrb[j] - '0' + add;
            if (t >= 2) {
                s.append(t % 2);
                add = t / 2;
            } else {
                s.append(t);
                add = 0;
            }
            i--;
            j--;
        }
        while (i >= 0) {
            if (add > 0) {
                int t = arra[i] - '0' + add;
                if (t >= 2) {
                    s.append(t % 2);
                    add = t / 2;
                } else {
                    s.append(t);
                    add = 0;
                }
            } else {
                s.append(arra[i] - '0');
            }
            i--;
        }

        while (j >= 0) {
            if (add > 0) {
                int t = arrb[j] - '0' + add;
                if (t >= 2) {
                    s.append(t % 2);
                    add = t / 2;
                } else {
                    s.append(t);
                    add = 0;
                }
            } else {
                s.append(arrb[j] - '0');
            }
            j--;
        }
        if (add > 0) {
            s.append(add);
        }
        return s.reverse().toString();
    }

    /**
     * x 的平方根
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x == 1) {
            return 1;
        }
        int l = 0;
        int r = x;
        int ans = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    /**
     * 爬楼梯
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }

    /**
     * 简化路径
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        ArrayDeque stack = new ArrayDeque();
        String[] arr = path.split("/");
        for (String str : arr) {
            if ("..".equals(str)) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (str.length() > 0 && !".".equals(str)) {
                stack.push(str);
            }
        }
        StringBuilder s = new StringBuilder();
        if (stack.isEmpty()) {
            return "/";
        }
        while (!stack.isEmpty()) {
            s.append("/");
            s.append(stack.pollLast());
        }
        return s.toString();
    }

    /**
     * 矩阵置零
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {

        boolean row0 = false;

        boolean col0 = false;

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                col0 = true;
                break;
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                row0 = true;
                break;
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < matrix[i].length; j++) {
                    if (matrix[i][j] != 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    if (matrix[i][j] != 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }
        if (col0) {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] != 0) {
                    matrix[i][0] = 0;
                }
            }
        }

        if (row0) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[0][j] != 0) {
                    matrix[0][j] = 0;
                }
            }
        }

    }

    /**
     * 搜索二维矩阵
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
//        int row = matrix.length - 1;
//        int col = 0;
//        while (row >= 0 && col <= matrix[0].length - 1) {
//            int num = matrix[row][col];
//            if (num == target) {
//                return true;
//            } else if (num > target) {
//                row--;
//            } else {
//                col++;
//            }
//        }
        int row = 0;
        int col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            int num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num > target) {
                col--;
            } else {
                row++;
            }
        }
        return false;
    }

    /**
     * 颜色分类
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
        int num0 = 0;
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                num0++;
            } else if (nums[i] == 1) {
                num1++;
            } else if (nums[i] == 2) {
                num2++;
            }
        }
        int k = 0;
        while (num0 > 0) {
            nums[k] = 0;
            k++;
            num0--;
        }
        while (num1 > 0) {
            nums[k] = 1;
            k++;
            num1--;
        }
        while (num2 > 0) {
            nums[k] = 2;
            k++;
            num2--;
        }
    }

    public void sortColors2(int[] nums) {
        int head = 0;
        int k = head;
        while (k < nums.length) {
            if (nums[k] == 0) {
                nums[k] = nums[head];
                nums[head] = 0;
                head++;
            }
            k++;
        }
        int n = head;
        while (n < nums.length) {
            if (nums[n] == 1) {
                nums[n] = nums[head];
                nums[head] = 1;
                head++;
            }
            n++;
        }
    }

    /**
     * 组合
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        back(result, sub, n, k);
        return result;
    }

    public void back(List<List<Integer>> result, List<Integer> sub, int n, int k) {
        if (sub.size() == k) {
            result.add(new ArrayList<>(sub));
            return;
        }
        for (int i = n; i >= 1; i--) {
            sub.add(i);
            back(result, sub, i - 1, k);
            sub.remove((sub.size() - 1));
        }
    }

    /**
     * 子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        backSub(result, sub, nums, 0);
        return result;
    }

    public void backSub(List<List<Integer>> result, List<Integer> sub, int[] nums, int k) {
        result.add(new ArrayList<>(sub));
        for (int i = k; i < nums.length; i++) {
            sub.add(nums[i]);
            backSub(result, sub, nums, i + 1);
            sub.remove((sub.size() - 1));
        }
    }

    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        backSub2(result, sub, nums, 0);
        return result;
    }

    public void backSub2(List<List<Integer>> result, List<Integer> sub, int[] nums, int k) {
        if (k == nums.length) {
            result.add(new ArrayList<>(sub));
            return;
        }
        sub.add(nums[k]);
        backSub2(result, sub, nums, k + 1);
        sub.remove((sub.size() - 1));
        backSub2(result, sub, nums, k + 1);
    }

    /**
     * 单词搜索
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        boolean[][] visited  = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (find(visited, board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find(boolean[][] visited,char[][] board, String word, int k, int i, int j) {
        if (k == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (board[i][j] == word.charAt(k)&&!visited[i][j]) {
            visited[i][j] = true;
            boolean flag = find(visited, board, word, k + 1, i + 1, j) || find(visited, board, word, k + 1, i, j + 1) || find(visited, board, word, k + 1, i, j - 1) || find(visited, board, word, k + 1, i - 1, j);
            if (flag) {
                return true;
            } else {
                visited[i][j] = false;
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 删除有序数组中的重复项 II
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if(nums.length<=2){
            return nums.length;
        }
        int l = nums.length;
        int k = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                k++;
                nums[k] = nums[i ];
            } else if (nums[i] != nums[i - 1]) {
                k++;
                nums[k] = nums[i];
            } else {
                l--;
            }
        }
        k++;
        nums[k] = nums[nums.length - 1];
        return l;
    }

    /**
     * 搜索旋转排序数组 II
     * @param nums
     * @param target
     * @return
     */
    public boolean search2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[left] == nums[mid] && nums[right] == nums[mid]) {
                left++;
                right--;
            } else if (nums[left] <= nums[mid] ) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 删除排序链表中的重复元素
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        while (node.next != null) {
            if (node.val == node.next.val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }
        return head;
    }

    /**
     * 分隔链表
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode large = new ListNode(0);
        ListNode smallH = small;
        ListNode largeH = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        small.next = largeH.next;
        large.next = null;
        return smallH.next;
    }

    /**
     * 合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        int k = m + n - 1;
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[k] = nums1[p1];
                k--;
                p1--;
            } else {
                nums1[k] = nums2[p2];
                k--;
                p2--;
            }
        }
        while (p1 >= 0) {
            nums1[k] = nums1[p1];
            k--;
            p1--;
        }
        while (p2 >= 0) {
            nums1[k] = nums2[p2];
            k--;
            p2--;
        }
    }

    /**
     * 格雷编码
     * @param n
     * @return
     */
//    public List<Integer> grayCode(int n) {
//
//    }

    /**
     * 子集 II
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> sub = new ArrayList<>();
        backSub2(false, result, sub, nums, 0);
        return result;
    }

    public void backSub2(boolean pre, List<List<Integer>> result, List<Integer> sub, int[] nums, int k) {
        if (k == nums.length) {
            result.add(new ArrayList<>(sub));
            return;
        }
        backSub2(false, result, sub, nums, k + 1);

        if (!pre && k >= 1 && nums[k] == nums[k - 1]) {
            return;
        }
        sub.add(nums[k]);
        backSub2(true, result, sub, nums, k + 1);
        sub.remove((sub.size() - 1));
    }

    /**
     * 解码方法
     * @param s
     * @return
     */
    public int numDecodings(String s) {

        int[] dp = new int[s.length() + 1];

        dp[0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] = dp[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0')) <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }


    /**
     * 反转链表 II
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode temp1 = new ListNode(-1);
        temp1.next = head;
        ListNode lNode = head;
        ListNode lNodePre = temp1;
        for(int i=0;i<left-1;i++){
            lNodePre = lNodePre.next;
        }

        ListNode rNode = lNodePre;
        ListNode rNodeAfter = null;
        for (int i = 0; i < right - left+1 ; i++) {
            rNode = rNode.next;
        }
        lNode = lNodePre.next;

        rNodeAfter = rNode.next;

        lNodePre.next = null;
        rNode.next = null;

        LinkedListAlgorithm.reverse(lNode);

        lNodePre.next = rNode;

        lNode.next = rNodeAfter;

        return temp1.next;
    }


    /**
     * 复原 IP 地址
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        List<String> sub = new ArrayList<>();
        restoreIpAddressesSub(s, 0, 1, result, sub);
        return result;
    }

    public void restoreIpAddressesSub(String s, int start, int k, List<String> result, List<String> sub) {
        if (start == s.length() && sub.size() == 4) {
            result.add(String.join(".", sub));
            return;
        }
        if (sub.size() > 4) {
            return;
        }
        for (int i = start; i < start + 3 && i < s.length(); i++) {
            if (s.charAt(start) == '0') {
                sub.add("0");
                restoreIpAddressesSub(s, i + 1, k + 1, result, sub);
                sub.remove(sub.size() - 1);
                return;
            }
            String str = s.substring(start, i+1);
            if (Integer.valueOf(str) <= 255) {
                sub.add(str);
                restoreIpAddressesSub(s, i + 1, k + 1, result, sub);
                sub.remove(sub.size() - 1);
            }
        }
    }

    /**
     * 二叉树的中序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversalSub(result, root);
        return result;

    }

    public void inorderTraversalSub(List<Integer> result,TreeNode root){
        if (root == null) {
            return;
        }

        inorderTraversalSub(result,root.left);

        result.add(root.val);

        inorderTraversalSub(result,root.right);
    }

    /**
     * 不同的二叉搜索树 II
     *
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        return generateTreesSub(1, n);

    }

    public List<TreeNode> generateTreesSub(int min, int max) {
        List<TreeNode> treeNodes = new ArrayList<>();
        if (min > max) {
            treeNodes.add(null);
            return treeNodes;
        }
        for (int i = min; i <= max; i++) {
            List<TreeNode> leftNodes = generateTreesSub(min, i - 1);
            List<TreeNode> rightNodes = generateTreesSub(i + 1, max);
            for (TreeNode leftNode : leftNodes) {
                for (TreeNode rightNode : rightNodes) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftNode;
                    treeNode.right = rightNode;
                    treeNodes.add(treeNode);
                }
            }
        }
        return treeNodes;
    }

    /**
     * 不同的二叉搜索树
     * g(n) = f(1)+f(2)+...+f(n)
     * f(i) = g(i-1)*g(n-i)
     * g(n) = g(0)*g(n-1)+g(1)*g(n-2)+...g(n-1)*g(0)
     * g(2) = g(0)*g(1)+g(1)*g(0)
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 交错字符串
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                int k = i + j - 1;
                if (i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i-1) == s3.charAt(k));
                }
                if (j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k));
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    /**
     * 验证二叉搜索树
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBSTSub(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBSTSub(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return isValidBSTSub(root.left, min, root.val) && isValidBSTSub(root.right, root.val, max);
    }




    TreeNode xNode = null;
    TreeNode yNode = null;
    TreeNode preNode = null;
    /**
     * 恢复二叉搜索树
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {

        recoverTreeSub(root);

        if(xNode!=null&&yNode!=null){
            int temp = xNode.val;
            xNode.val = yNode.val;
            yNode.val = temp;
        }

    }

    public void recoverTreeSub( TreeNode root){

        if (root == null) {
            return;
        }
        recoverTreeSub(root.left);
        if (preNode != null) {
            if (preNode.val > root.val) {
                if (xNode == null) {
                    xNode = preNode;
                    yNode = root;
                } else {
                    yNode = root;
                }
            }
        }
        preNode = root;
        recoverTreeSub(root.right);

    }

    /**
     * 相同的树
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 对称二叉树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return isSymmetricSub(root,root);
    }

    public boolean isSymmetricSub(TreeNode p,TreeNode q) {
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val != q.val) {
            return false;
        }
        return isSymmetricSub(p.left, q.right) && isSymmetricSub(p.right, q.left);
    }


}
