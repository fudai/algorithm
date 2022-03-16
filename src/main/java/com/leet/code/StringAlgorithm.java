/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: StringAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-14 11:08
 * @version: V1.0
 */
package com.leet.code;


import java.util.*;

/**
 * @className: Algorithm
 * @description:
 * @author: fudai
 * @date: 2021-07-14 11:08
 */
public class StringAlgorithm {

    /**
     * 最小覆盖子串
     *
     * @param s 主串
     * @param t 子串
     * @return 最小覆盖子串
     */
    public static String minWindow(String s, String t) {
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();
        Map<Character, Integer> resultMap = new HashMap<Character, Integer>();
        char[] str = s.toCharArray();
        char[] sub = t.toCharArray();
        for (char c : sub) {
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else {
                tMap.put(c, 1);
            }
        }
        int left = 0;
        int right = 0;
        int length = 0;
        int start = left;
        while (right < str.length) {
            char c = str[right];
            right++;
            if (resultMap.containsKey(c)) {
                resultMap.put(c, resultMap.get(c) + 1);
            } else {
                resultMap.put(c, 1);
            }
            while (isMatch(tMap, resultMap)) {
                if (tMap.containsKey(str[left])) {
                    resultMap.put(str[left], resultMap.get(str[left]) - 1);
                }
                if (length == 0 || length > right - left) {
                    length = right - left;
                    start = left;
                }
                left++;
            }
        }
        if (length > 0) {
            return s.substring(start, start + length);
        } else {
            return "";
        }
    }

    private static boolean isMatch(Map<Character, Integer> tMap, Map<Character, Integer> resultMap) {
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            if (resultMap.get(entry.getKey()) == null || resultMap.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断子串是否在主串中（完整匹配）
     *
     * @param s1 主串
     * @param s2 子串
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();
        Map<Character, Integer> resultMap = new HashMap<Character, Integer>();
        char[] str = s1.toCharArray();
        char[] sub = s2.toCharArray();
        for (char c : sub) {
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else {
                tMap.put(c, 1);
            }
        }
        int left = 0;
        int right = 0;
        while (right < str.length) {
            char c = str[right];
            right++;
            if (resultMap.containsKey(c)) {
                resultMap.put(c, resultMap.get(c) + 1);
            } else {
                resultMap.put(c, 1);
            }
            while (isMatch(tMap, resultMap)) {
                if (right - left == sub.length) return true;
                if (tMap.containsKey(str[left])) {
                    resultMap.put(str[left], resultMap.get(str[left]) - 1);
                }
                left++;
            }
        }
        return false;
    }

    /**
     * 找到字符串中所有字母异位词
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> find = new ArrayList<>();
        Map<Character, Integer> tMap = new HashMap<Character, Integer>();
        Map<Character, Integer> resultMap = new HashMap<Character, Integer>();
        char[] str = s.toCharArray();
        char[] sub = p.toCharArray();
        for (char c : sub) {
            if (tMap.containsKey(c)) {
                tMap.put(c, tMap.get(c) + 1);
            } else {
                tMap.put(c, 1);
            }
        }
        int left = 0;
        int right = 0;
        while (right < str.length) {
            char c = str[right];
            right++;
            if (resultMap.containsKey(c)) {
                resultMap.put(c, resultMap.get(c) + 1);
            } else {
                resultMap.put(c, 1);
            }
            while (isMatch(tMap, resultMap)) {
                if (right - left == p.length() && !s.substring(left, right).equals(p)) find.add(left);
                if (tMap.containsKey(str[left])) {
                    resultMap.put(str[left], resultMap.get(str[left]) - 1);
                }
                left++;
            }
        }
        return find;
    }

    /**
     * 最长的没有重复字符的子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> resultMap = new HashMap<Character, Integer>();
        char[] str = s.toCharArray();
        int left = 0;
        int right = 0;
        int result = 0;
        while (right < str.length) {
            char c = str[right];
            right++;
            if (resultMap.containsKey(c)) {
                resultMap.put(c, resultMap.get(c) + 1);
            } else {
                resultMap.put(c, 1);
            }
            while (resultMap.get(c) > 1) {
                if (resultMap.containsKey(str[left])) {
                    resultMap.put(str[left], resultMap.get(str[left]) - 1);
                }
                left++;
            }
            result = Math.max(result, right - left);
        }
        return result;
    }

    /**
     * 打开转盘锁
     *
     * @param deadends 死亡数字
     * @param target   目标数字
     * @return
     */
    public static int openLock(String[] deadends, String target) {
        int step = 0;
        Queue<String> queue = new LinkedList();
        Set<String> deadSet = new HashSet<String>();
        for (int i = 0; i < deadends.length; i++) deadSet.add(deadends[i]);
        Set<String> visited = new HashSet<String>();
        queue.offer("0000");
        visited.add("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String one = queue.poll();
                if (deadSet.contains(one)) continue;
                if (target.equals(one)) return step;
                for (int j = 0; j < 4; j++) {
                    String upOne = upOne(one, j);
                    if (!visited.contains(upOne)) {
                        queue.offer(upOne);
                        visited.add(upOne);
                    }
                    String downOne = downOne(one, j);
                    if (!visited.contains(downOne)) {
                        queue.offer(downOne);
                        visited.add(downOne);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    public static String upOne(String one, int k) {
        char[] chars = one.toCharArray();
        if (chars[k] == '9') {
            chars[k] = '0';
        } else {
            chars[k]++;
        }
        return new String(chars);
    }

    public static String downOne(String one, int k) {
        char[] chars = one.toCharArray();
        if (chars[k] == '0') {
            chars[k] = '9';
        } else {
            chars[k]--;
        }
        return new String(chars);
    }

    /**
     * 最长公共子序列
     * dp[i][j] 子串 1-i,1-j的公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        char[] arr1 = text1.toCharArray();
        char[] arr2 = text2.toCharArray();
        int[][] dp = new int[arr1.length + 1][arr2.length + 1];
        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {
                if (arr1[i - 1] == arr2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    /**
     * 最长回文子序列
     *
     * @param s
     * @return
     */
    public static int longestPalindromeSubseq(String s) {
        char[] arr = s.toCharArray();
        int[][] dp = new int[arr.length][arr.length];

        for (int i = 0; i < arr.length; i++) {
            dp[i][i] = 1;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][arr.length - 1];
    }

    /**
     * 两个字符串的最小ASCII删除和
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int minimumDeleteSum(String s1, String s2) {
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        int[][] dp = new int[array1.length + 1][array2.length + 1];
        for (int i = 1; i < array1.length + 1; i++) {
            dp[i][0] = sum(array1, i);
        }
        for (int j = 1; j < array2.length + 1; j++) {
            dp[0][j] = sum(array2, j);
        }
        for (int i = 1; i <= array1.length; i++) {
            for (int j = 1; j <= array2.length; j++) {
                if (array1[i - 1] == array2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + array1[i - 1], dp[i][j - 1] + array2[j - 1]);
                }
            }
        }
        return dp[array1.length][array2.length];
    }

    public static int sum(char[] array, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    /**
     * 是否匹配
     *
     * @param s 字符串
     * @param p 正则表达式
     * @return
     */
    public static boolean isMatch(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (firstMatch && isMatch(s.substring(1), p)) || isMatch(s, p.substring(2));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    static Map<String, Boolean> memoMatch = new HashMap<>();

    /**
     * 是否匹配
     *
     * @param s 字符串
     * @param p 正则表达式
     * @return
     */
    public static boolean isMatch2(String s, String p) {

        return dp(0, 0, s, p);

    }

    public static boolean dp(int i, int j, String s, String p) {
        if (memoMatch.containsKey(i + "," + j)) {
            return memoMatch.get(i + "," + j);
        }
        if (j == p.length()) {
            return i == s.length();
        }
        boolean ans;
        boolean firstMatch = i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.');
        if (p.length() - 2 >= j && p.charAt(j + 1) == '*') {
            ans = (firstMatch && dp(i + 1, j, s, p)) || dp(i, j + 2, s, p);
        } else {
            ans = firstMatch && dp(i + 1, j + 1, s, p);
        }
        memoMatch.put(i + "," + j, ans);
        return ans;
    }

    /**
     * 最长回文子串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        char[] array = s.toCharArray();
        String result = "";
        for (int i = 0; i < array.length; i++) {
            String s1 = palindrome(array, i, i);
            String s2 = palindrome(array, i, i + 1);
            result = result.length() > s1.length() ? result : s1;
            result = result.length() > s2.length() ? result : s2;
        }
        return result;
    }

    /**
     * 以 l 到 r 为中心的回文子串
     *
     * @param array
     * @param l
     * @param r
     * @return
     */
    public static String palindrome(char[] array, int l, int r) {
        while (l >= 0 && r < array.length && array[l] == array[r]) {
            l--;
            r++;
        }
        StringBuilder s = new StringBuilder();
        for (int i = l + 1; i < r; i++) {
            s.append(array[i]);
        }
        return s.toString();
    }

    /**
     * 最长回文子串（动态规划解法）
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        char[] array = s.toCharArray();
        //dp[i][j] i到j之间是否是回文串
        boolean[][] dp = new boolean[array.length][array.length];
        String result = "";
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = i; j < array.length; j++) {
                dp[i][j] = array[i] == array[j] && (j - i < 2 || dp[i + 1][j - 1]);
                if (dp[i][j] && j - i + 1 > result.length()) {
                    result = s.substring(i, j + 1);
                }
            }
        }
        return result;
    }

}
