/**
 * Copyright (c) 2009-2020 WACAI,Inc.All Rights Reserved.
 *
 * @fileName: Test
 * @package: PACKAGE_NAME
 * @date: 2020-09-07 18:23
 * @version: V1.0
 */

import org.junit.Assert;

import java.util.*;

/**
 * @className: Test
 * @description:
 * @author: fudai
 * @date: 2020-09-07 18:23
 */
public class Test {

    /**
     * 打开转盘锁
     *
     * @param deadends 死亡数字
     * @param target   目标数字
     * @return
     */
    public int openLock(String[] deadends, String target) {
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

    public String upOne(String one, int k) {
        char[] chars = one.toCharArray();
        if (chars[k] == '9') {
            chars[k] = '0';
        } else {
            chars[k]++;
        }
        return new String(chars);
    }

    public String downOne(String one, int k) {
        char[] chars = one.toCharArray();
        if (chars[k] == '0') {
            chars[k] = '9';
        } else {
            chars[k]--;
        }
        return new String(chars);
    }

    /**
     * 二分查找（单个）
     *
     * @param nums   升序数组
     * @param target 目标数字
     * @return 满足的下标
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) {
                right = mid - 1;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查出（范围）
     *
     * @param nums   递增数组（可重复）
     * @param target 目标数字
     * @return 满足的下标范围
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int[] result = {-1, -1};
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) right = mid - 1;
            if (nums[mid] > target) right = mid - 1;
            if (nums[mid] < target) left = mid + 1;
        }
        result[0] = left >= nums.length || nums[left] != target ? -1 : left;

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) left = mid + 1;
            if (nums[mid] > target) right = mid - 1;
            if (nums[mid] < target) left = mid + 1;
        }
        result[1] = right >= nums.length || nums[right] != target ? -1 : right;
        return result;
    }

    /**
     * 最小覆盖子串
     *
     * @param s 主串
     * @param t 子串
     * @return 最小覆盖子串
     */
    public String minWindow(String s, String t) {
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

    @org.junit.Test
    public void minWindowTest() {
        System.out.println(minWindow("1234567890", "135"));
    }

    private boolean isMatch(Map<Character, Integer> tMap, Map<Character, Integer> resultMap) {
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
    public boolean checkInclusion(String s1, String s2) {
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

    @org.junit.Test
    public void checkInclusionTest() {
        System.out.println(checkInclusion("1234567890", "56"));
    }

    /**
     * 找到字符串中所有字母异位词
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
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

    @org.junit.Test
    public void findAnagramsTest() {
        System.out.println(findAnagrams("1234567890", "68"));
    }

    /**
     * 最长的没有重复字符的子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
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
     * for（i ）
     *  for（j ）
     *     for（ k ）
     *     i:天数
     *     j:至今最多交易次数
     *     k:是否持有
     *     dp[i][j][0]=max(dp[i-1][j][0],dp[i-1][j][1]+price[i])
     *     dp[i][j][1]=max(dp[i-1][j-1][0]-price[i],dp[i-1][j][1])
     */

    /**
     * 买卖该股票一次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dpTable[i][0] = 0;
                dpTable[i][1] = -prices[i];
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], -prices[i]);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 买卖该股票一次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfitOpt(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int dp0 = 0;
        int dp1 = 0;
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dp0 = 0;
                dp1 = -prices[i];
                continue;
            }
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, -prices[i]);
        }
        return dp0;
    }


    /**
     * 买卖该股票不限制次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dpTable[i][0] = 0;
                dpTable[i][1] = -prices[i];
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], dpTable[i - 1][0] - prices[i]);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 买卖该股票不限制次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit2Opt(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int dp0 = 0;
        int dp1 = -prices[0];
        for (int i = 1; i < days; i++) {
            dp0 = Math.max(dp0, dp1 + prices[i]);
            dp1 = Math.max(dp1, dp0 - prices[i]);
        }
        return dp0;
    }

    /**
     * 买卖该股票2次可能获得的最大利润
     * 时间复杂度：O(n),空间复杂度：O(n)
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int k = 3;
        int[][][] dpTable = new int[days][k][2];
        for (int i = 0; i < days; i++) {
            if (i == 0) {
                dpTable[i][1][0] = 0;
                dpTable[i][1][1] = -prices[i];
                dpTable[i][2][0] = 0;
                dpTable[i][2][1] = -prices[i];
                continue;
            }
            dpTable[i][1][0] = Math.max(dpTable[i - 1][1][0], dpTable[i - 1][1][1] + prices[i]);
            dpTable[i][1][1] = Math.max(dpTable[i - 1][1][1], -prices[i]);
            dpTable[i][2][0] = Math.max(dpTable[i - 1][2][0], dpTable[i - 1][2][1] + prices[i]);
            dpTable[i][2][1] = Math.max(dpTable[i - 1][2][1], dpTable[i - 1][1][0] - prices[i]);
            System.out.println(i + ": " + dpTable[i][1][0] + " " + dpTable[i][1][1] + " " + dpTable[i][2][0] + " " + dpTable[i][2][1]);
        }
        return dpTable[days - 1][2][0];
    }

    /**
     * 买卖该股票k次可能获得的最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        if (k == 0) return 0;
        int[][][] dpTable = new int[days][k + 1][2];
        for (int i = 0; i < days; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dpTable[0][j][0] = 0;
                    dpTable[0][j][1] = -prices[i];
                    continue;
                }
                dpTable[i][j][0] = Math.max(dpTable[i - 1][j][0], dpTable[i - 1][j][1] + prices[i]);
                dpTable[i][j][1] = Math.max(dpTable[i - 1][j][1], dpTable[i - 1][j - 1][0] - prices[i]);
//                System.out.println("i:" + i + " j:" + j + " 0    " + dpTable[i][j][0]);
//                System.out.println("i:" + i + " j:" + j + " 1    " + dpTable[i][j][1]);
            }
        }
        return dpTable[days - 1][k][0];

    }

    /**
     * 买卖该股票k次可能获得的最大利润（降低空间复杂度）
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int k, int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        if (k == 0) return 0;
        k = Math.min(k, days / 2);
        int dp_i_j_0 = 0;
        int dp_i_j_1 = 0;
        int dp_i_j_pre_0 = 0;
        for (int i = 0; i < days; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp_i_j_0 = 0;
                    dp_i_j_1 = -prices[i];
                    continue;
                }
                dp_i_j_pre_0 = dp_i_j_0;
                dp_i_j_0 = Math.max(dp_i_j_0, dp_i_j_1 + prices[i]);
                dp_i_j_1 = Math.max(dp_i_j_1, dp_i_j_pre_0 - prices[i]);
            }
        }
        return dp_i_j_0;

    }

    /**
     * 股票买卖最大收益（可以进行无数次交易，每次卖之后要等一天才可以交易）
     *
     * @param prices
     * @return
     */
    public int maxProfit5(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 == -1) {
                dpTable[0][0] = 0;
                dpTable[0][1] = -prices[0];
                continue;
            }
            if (i - 1 == 0) {
                dpTable[1][0] = Math.max(dpTable[0][0], dpTable[0][1] + prices[1]);
                dpTable[1][1] = Math.max(dpTable[0][1], -prices[1]);
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], dpTable[i - 2][0] - prices[i]);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 股票买卖最大收益（可以进行无数次交易，每次交易要支付手续费）
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit6(int[] prices, int fee) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dpTable = new int[days][2];
        for (int i = 0; i < days; i++) {
            if (i - 1 < 0) {
                dpTable[i][0] = 0;
                dpTable[i][1] = -prices[i] - fee;
                continue;
            }
            dpTable[i][0] = Math.max(dpTable[i - 1][0], dpTable[i - 1][1] + prices[i]);
            dpTable[i][1] = Math.max(dpTable[i - 1][1], dpTable[i - 1][0] - prices[i] - fee);
        }
        return dpTable[days - 1][0];
    }

    /**
     * 链表反转（递归实现）
     * @param head 1->2->3->4->5->null
     *             1->2<-3<-4<-5
     * @return
     */
    public ListNode reverse(ListNode head) {
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
     * @param head 1->2->3->4->5->null
     * null <-1 <- 2 <- 3 <- 4 <- 5 null
     * @return
     */
    public ListNode reverse2(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while (cur != null) {
            next =cur.next;
            cur.next=pre;
            pre=cur;
            cur = next;
        }
        return pre;
    }

    @org.junit.Test
    public void reverseTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
        ListNode newNode = reverse(head1);
        System.out.println(newNode);
    }

    @org.junit.Test
    public void reverse2Test() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
        ListNode newNode = reverse2(head1);
        System.out.println(newNode);
    }

    /**
     * 链表前n个反转（递归实现）
     *
     * @param head 1->2->3->4->5->null
     *             1->3->2->4->5->null
     * @return
     */
    public ListNode afterNode;
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            afterNode = head.next;
            return head;
        }
        ListNode subNode = reverseN(head.next, n - 1);
        head.next.next=head;
        head.next = afterNode;
        return subNode;
    }

    /**
     * 链表前n个反转（迭代实现）
     *
     * @param head
     * @return null <- 1 <- 2  3 -> 4 -> 5 -> null
     */
    public ListNode reverseN2(ListNode head, int n) {
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

    @org.junit.Test
    public void reverseNTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        ListNode newNode = reverseN(head1, 3);
        System.out.println(newNode);
    }

    @org.junit.Test
    public void reverseN2Test() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        ListNode newNode = reverseN2(head1, 3);
        System.out.println(newNode);
    }

    /**
     * 链表前m-n个反转（递归实现）
     *
     * @param head
     * @return
     */
    public ListNode reverseMN(ListNode head, int m, int n) {
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
    public ListNode reverseMN2(ListNode head, int m, int n) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;
        ListNode mNode =null;
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
        mNode.next.next=pre;
        mNode.next = cur;
        return head;
    }

    @org.junit.Test
    public void reverseMN2Test() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        ListNode newNode = reverseMN2(head1, 2, 4);
        System.out.println(newNode);
    }

    @org.junit.Test
    public void reverseMNTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        ListNode newNode = reverseMN(head1, 2, 4);
        System.out.println(newNode);
    }


    ListNode left = null;

    /**
     * 判断回文单链表
     * 时间复杂度：O(n) 空间复杂度：O(n)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    public boolean traverse(ListNode right) {
        if (right == null) return true;
        boolean result = traverse(right.next);
        result = result && (right.val == left.val);
        left = left.next;
        return result;
    }

    /**
     * 判断回文单链表
     * 时间复杂度：O(n) 空间复杂度：O(1)
     * @param head
     * @return
     * 1 2 2 1
     * 1 2 3 2 1
     */
    public boolean isPalindrome2(ListNode head) {
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
    @org.junit.Test
    public void isPalindrome2Test(){
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(2);
        ListNode head5 = new ListNode(1);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        Assert.assertTrue(isPalindrome2(head1));
    }

    @org.junit.Test
    public void isPalindromeTest(){
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(2);
        ListNode head4 = new ListNode(1);
//        ListNode head5 = new ListNode(1);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
//        head4.next = head5;
        Assert.assertTrue(isPalindrome(head1));
    }

    /**
     * 盗贼抢劫（不可以抢劫相连的）递归实现
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int[] memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return dp(nums, memo, 0);
    }

    /**
     * 盗贼抢劫（不可以抢劫相连的）dp数组
     * @param nums
     * @return
     */
    public int rob1(int[] nums) {

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
    public int rob2(int[] nums) {

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
    public int rob3(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(dpRange(nums, 1, nums.length - 1), dpRange(nums, 0, nums.length - 2));
    }

    /**
     * 盗贼抢劫（不可以抢劫树中的相连节点）
     * @param treeNode
     * @return
     */
    public int rob4(TreeNode treeNode) {
        int[] res = dp2(treeNode);
        return Math.max(res[0], res[1]);
    }

    public int[] dp2(TreeNode treeNode) {
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
    private int dpRange(int[] nums, int start, int end) {
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

    public int dp(int[] nums, int[] memo, int i) {
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

    /**
     * 删除被覆盖区间
     * @param intervals
     * @return 删除后的区间个数
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        int result = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = intervals[i];
            if (left <= temp[0] && temp[1] <= right) {
                result++;
            } else if (left <= temp[0] && right < temp[1]) {
                right = temp[1];
            } else if (temp[0] > right) {
                left = temp[0];
                right = temp[1];
            }
        }
        return intervals.length - result;

    }

    /**
     * 合并重叠区间
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[][]{};
        }
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        int left = intervals[0][0];
        int right = intervals[0][1];
        int[][] result = new int[intervals.length][2];
        int k = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] temp = intervals[i];
            if (temp[0] <= right) {
                right = Math.max(temp[1], right);
            } else {
                result[k][0] = left;
                result[k][1] = right;
                left = temp[0];
                right = temp[1];
                k++;
            }
            if (i == intervals.length - 1) {
                result[k][0] = left;
                result[k][1] = right;
            }
        }
        return Arrays.copyOfRange(result, 0, k);
    }

    /**
     * 区间交集问题
     * @param A
     * @param B
     * @return
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        if (A.length == 0 || B.length == 0) {
            return new int[][]{};
        }
        int i = 0;
        int j = 0;
        int k = 0;
        int[][] result = new int[A.length + B.length][2];
        while (i < A.length && j < B.length) {
            int a1 = A[i][0];
            int a2 = A[i][1];
            int b1 = B[j][0];
            int b2 = B[j][1];
            if (a2 >= b1 && a1 <= b2) {
                result[k][0] = Math.max(a1, b1);
                result[k][1] = Math.min(a2, b2);
                k++;
            }
            if (a2 > b2) {
                j++;
            } else {
                i++;
            }
        }
        return Arrays.copyOfRange(result, 0, k + 1);

    }

    /**
     * 两数和问题 暴力解法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 两数和问题 优化解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == target) {
                return new int[]{nums[i], nums[j]};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[]{};
    }

    @org.junit.Test
    public void twoSum2Test(){
        int[] nums = {1,7,2,8,11};
        int target = 10;
        System.out.println(Arrays.toString(twoSum2(nums,target)));
    }

    /**
     * 三数和问题
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (i - 1 >= 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            while (left < right) {
                if (i != left && i != right && nums[left] + nums[right] == -nums[i]) {
                    while (left + 1 < nums.length - 1 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right - 1 >= 0 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    List<Integer> resultSub = new ArrayList<>();
                    resultSub.add(nums[i]);
                    resultSub.add(nums[left]);
                    resultSub.add(nums[right]);
                    result.add(resultSub);
                    left++;
                }
                if (i == left || nums[left] + nums[right] > -nums[i]) {
                    right--;
                } else if (i == right || nums[left] + nums[right] < -nums[i]) {
                    left++;
                }
            }

        }
        return result;
    }

    /**
     * 四数和问题
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            if (i - 1 >= 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if (j - 1 >= 0 && nums[j - 1] == nums[j] && i != j - 1) {
                    continue;
                }
                int left = j + 1;
                int right = nums.length - 1;
                while (left < right) {
                    if (i != left && i != right && j != left && j != right && (nums[left] + nums[right] + nums[i] + nums[j]) == target) {
                        while (left + 1 < nums.length - 1 && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (right - 1 >= 0 && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        List<Integer> resultSub = new ArrayList<>();
                        resultSub.add(nums[i]);
                        resultSub.add(nums[j]);
                        resultSub.add(nums[left]);
                        resultSub.add(nums[right]);
                        result.add(resultSub);
                        left++;
                    }
                    if (i == left || j == left || nums[left] + nums[right] + nums[i] + nums[j] > target) {
                        right--;
                    }
                    if (i == right || j == right || nums[left] + nums[right] + nums[i] + nums[j] < target) {
                        left++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * n和问题
     * @param nums
     * @param n
     * @param target
     * @return
     */
    public List<List<Integer>> nSum(int[] nums, int n, int target) {
        Arrays.sort(nums);
        return nSumTarget(nums, n, 0,target);
    }

    public List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            for (int num : nums) {
                if (num == target) {
                    List<Integer> subResult = new ArrayList<>();
                    subResult.add(num);
                    result.add(subResult);
                    return result;
                }
            }
            return result;
        }
        if (n < 1 || n > nums.length) return result;
        if (n == 2) {
            int left = start;
            int right = nums.length - 1;
            while (left < right) {
                int lNum = nums[left];
                int rNum = nums[right];
                if (lNum + rNum == target) {
                    List<Integer> subResult = new ArrayList<>();
                    subResult.add(nums[left]);
                    subResult.add(nums[right]);
                    result.add(subResult);
                    while (left < right && nums[left] == lNum) {
                        left++;
                    }
                    while (left < right && nums[right] == rNum) {
                        right--;
                    }
                } else if (lNum + rNum < target) {
                    while (left < right && nums[left] == lNum) {
                        left++;
                    }
                } else {
                    while (left < right && nums[right] == rNum) {
                        right--;
                    }
                }
            }
        } else {
            int pre = 0;
            for (int i = start; i < nums.length; i++) {
                if (pre == nums[i]) {
                    continue;
                }
                int num = nums[i];
                List<List<Integer>> subResult = nSumTarget(nums, n - 1, i + 1, target - nums[i]);
                subResult.forEach(sub -> {
                    sub.add(num);
                    result.add(sub);
                });
                pre = nums[i];
            }
        }
        return result;
    }

    @org.junit.Test
    public void nSumTest(){
        int[] nums = new int[]{1,2,3,4,5,6,7,8,3,4,2};
        System.out.println(nSum(nums,4,10));
    }

    /**
     * 高楼扔鸡蛋问题（递归解法，二分查找优化）
     * @param K 鸡蛋个数
     * @param N 楼层数
     * @return
     */
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        return dpEgg2Split(K, N, dp);
    }

    /**
     * 高楼扔鸡蛋问题（迭代解法）
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop2(int K, int N) {
        int[][] dp = new int[K + 1][N + 1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++) {
                dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1;
            }
        }
        return m;
    }

    /**
     * 高楼扔鸡蛋问题（递归解法）
     * @param K
     * @param N
     * @param dp
     * @return
     */
    private int dpEgg(int K, int N, int[][] dp) {
        if (K == 1) {
            dp[K][N] = N;
            return N;
        }
        if (N == 0 || K == 0) {
            dp[K][N] = 0;
            return 0;
        }
//        if (N == 1 && K >= 1) {
//            dp[K][N] = 0;
//            return 0;
//        }
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        int result = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            result = Math.min(result, Math.max(dpEgg(K, N - i, dp), dpEgg(K - 1, i - 1, dp)) + 1);
        }
        dp[K][N] = result;
        return result;
    }


    private int dpEgg2Split(int K, int N, int[][] dp) {
        if (K == 1) {
            dp[K][N] = N;
            return N;
        }
        if (N == 0 || K == 0) {
            dp[K][N] = 0;
            return 0;
        }
        if (dp[K][N] > 0) {
            return dp[K][N];
        }
        int result = Integer.MAX_VALUE;
        int left = 1;
        int right = N;
        while (left <= right) {
            int mid = (left + right) / 2;
            int noResult = dpEgg2Split(K, N - mid, dp);
            int yesResult = dpEgg2Split(K - 1, mid - 1, dp);
            if (noResult > yesResult) {
                left = mid + 1;
                result = Math.min(result, noResult + 1);
            } else {
                right = mid - 1;
                result = Math.min(result, yesResult + 1);
            }
        }
        dp[K][N] = result;
        System.out.println("K:" + K + " N:" + N + "  result:" + result);
        return result;
    }

    /**
     * 翻转二叉树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        reverse(root);
        return root;
    }

    private void reverse(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        reverse(node.left);
        reverse(node.right);
    }

    /**
     * 二叉树展开为链表
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        before(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            TreeNode curr = result.get(i);
            curr.left = null;
            curr.right = result.get(i + 1);
        }
    }

    /**
     * 前序遍历
     *
     * @param node
     * @param result
     */
    public void before(TreeNode node, List<TreeNode> result) {
        if (node != null) {
            result.add(node);
            before(node.left, result);
            before(node.right, result);
        }
    }

    /**
     * 填充二叉树节点的右侧指针（迭代）
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node leftNode = root;
        while (leftNode.left != null) {
            Node begin = leftNode;
            while (begin != null) {
                begin.left.next = begin.right;
                if (begin.next != null) {
                    begin.right.next = begin.next.left;
                }
                begin = begin.next;
            }
            leftNode = leftNode.left;
        }
        return root;
    }

    /**
     * 填充二叉树节点的右侧指针（递归）
     *
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    public void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node1.right, node2.left);
        connectTwoNode(node2.left, node2.right);
    }

    @org.junit.Test
    public void connect2Test() {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        Node newNode = connect2(root);
        System.out.println(newNode);
    }

    /**
     * 构造最大二叉树
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public TreeNode build(int[] nums, int left, int right) {
        if (nums.length == 0 || left > right) {
            return null;
        }
        int max = 0;
        int key = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                key = i;
            }
        }
        TreeNode treeNode = new TreeNode(max);
        treeNode.left = build(nums, left, key - 1);
        treeNode.right = build(nums, key + 1, right);
        return treeNode;
    }

    /**
     * 根据前序和中序得出树
     *
     * @param preorder 前序遍历
     * @param inorder  中序遍历
     * @return 树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildNode(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }

    public TreeNode buildNode(int[] preorder, int[] inorder, int preleft, int preright, int inleft, int inright, Map<Integer, Integer> inMap) {
        if (preleft > preright || inleft > inright || preleft < 0 || preright < 0 || inleft < 0 || inright < 0) {
            return null;
        }
        int inrootP = inMap.get(preorder[preleft]);
        TreeNode treeNode = new TreeNode(preorder[preleft]);
        treeNode.left = buildNode(preorder, inorder, preleft + 1, preleft + inrootP - inleft, inleft, inrootP - 1, inMap);
        treeNode.right = buildNode(preorder, inorder, preleft + inrootP - inleft + 1, preright, inrootP + 1, inright, inMap);
        return treeNode;
    }

    /**
     * 根据后序和中序得出树
     *
     * @param inorder   中序
     * @param postorder 后序
     * @return 树
     */
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildNode2(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, inMap);
    }

    public TreeNode buildNode2(int[] inorder, int[] postorder, int inleft, int inright, int postleft, int postright, Map<Integer, Integer> inMap) {
        if (postleft > postright || inleft > inright || postleft < 0 || postright < 0 || inleft < 0 || inright < 0) {
            return null;
        }
        int inrootP = inMap.get(postorder[postright]);
        TreeNode treeNode = new TreeNode(postorder[postright]);
        treeNode.left = buildNode2(inorder, postorder, inleft, inrootP - 1, postleft, postleft + (inrootP - inleft) - 1, inMap);
        treeNode.right = buildNode2(inorder, postorder, inrootP + 1, inright, postleft + (inrootP - inleft), postright - 1, inMap);
        return treeNode;
    }

    /**
     * 根据后序和前序得出树
     *
     * @param preorder  前序
     * @param postorder 后序
     * @return 树
     */
    public TreeNode buildTree3(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postMap.put(postorder[i], i);
        }
//           1
//        2     3
//      4    5 6  7
//            1,2,4,5,3,6,7
//            4,5,2,6,7,3,1
        return buildNode3(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1, postMap);
    }

    public TreeNode buildNode3(int[] preorder, int[] postorder, int preleft, int preright, int postleft, int postright, Map<Integer, Integer> postMap) {
        if (postleft > postright || preleft > preright || postleft < 0 || postright < 0 || preleft < 0 || preright < 0) {
            return null;
        }
        int postRootP = postMap.get(preorder[preleft + 1]);
        TreeNode treeNode = new TreeNode(postorder[postright]);
        treeNode.left = buildNode2(preorder, postorder, preleft + 1, preleft + postRootP + 1, postleft, postleft + postRootP, postMap);
        treeNode.right = buildNode2(preorder, postorder, preleft + postRootP + 2, preright, postRootP + 1, postright - 1, postMap);
        return treeNode;
    }


    /**
     * 恢复二叉搜索树（显式中序遍历）
     *
     * @param node
     */
    public void traverse1(TreeNode node) {
        List<TreeNode> intOrderList = new ArrayList<>();
        inorder(node, intOrderList);
        int x = -1;
        int y = -1;
        for (int i = 0; i < intOrderList.size() - 1; i++) {
            if (intOrderList.get(i).val > intOrderList.get(i + 1).val) {
                if (x == -1) {
                    x = i;
                    y = i + 1;
                } else {
                    y = i + 1;
                }
            }
        }
        recover(node, 2, intOrderList.get(x).val, intOrderList.get(y).val);
    }

    /**
     * 恢复二叉搜索树（隐式中序遍历）
     *
     * @param node
     */
    public void traverse2(TreeNode node) {
        TreeNode x = null;
        TreeNode y = null;
        inorder2(node, x, y, null);
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }

    //           3
//        5     7
//      1   4 6  8
//    1 2 6 4 5 3 7 8
    @org.junit.Test
    public void testTraverse2() {
        TreeNode node5 = new TreeNode(3);
        node5.left = new TreeNode(5);
        node5.right = new TreeNode(7);
        node5.left.left = new TreeNode(1);
        node5.left.right = new TreeNode(4);
        node5.right.left = new TreeNode(6);
        node5.right.right = new TreeNode(8);
        traverse2(node5);
        System.out.println(node5);
    }

    public void inorder2(TreeNode node, TreeNode x, TreeNode y, TreeNode pred) {
        if (node == null) return;
        inorder2(node.left, x, y, pred);
        if (pred != null && node.val < pred.val) {
            if (x == null) {
                x = node;
                y = pred;
            } else {
                x = pred;
            }
        }
        pred = node;
        inorder2(node.right, x, y, pred);
    }

    public void recover(TreeNode node, int count, int x, int y) {
        if (node == null) return;
        if (count == 0) return;
        if ((node.val == x || node.val == y) && count > 0) {
            if (node.val == x) {
                node.val = y;
            } else {
                node.val = x;
            }
            count--;
        }
        recover(node.left, count, x, y);
        recover(node.right, count, x, y);
    }

    /**
     * 中序遍历
     * @param node
     * @param treeNodeList
     */
    public void inorder(TreeNode node, List<TreeNode> treeNodeList) {
        if (node == null) return;
        inorder(node.left, treeNodeList);
        treeNodeList.add(node);
        inorder(node.right, treeNodeList);
    }

    //           5
//        6     7
//      1   4 3  8
//    1 2 6 4 5 3 7 8
    @org.junit.Test
    public void testTraverse1() {
        TreeNode node5 = new TreeNode(3);
        node5.left = new TreeNode(5);
        node5.right = new TreeNode(7);
        node5.left.left = new TreeNode(1);
        node5.left.right = new TreeNode(4);
        node5.right.left = new TreeNode(6);
        node5.right.right = new TreeNode(8);
        traverse1(node5);
        System.out.println(node5);
    }

    @org.junit.Test
    public void testBuildTree3() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
        TreeNode treeNode = buildTree3(preorder, postorder);
        System.out.println(treeNode);
    }

    /**
     * 寻找重复子树
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> memo = new HashMap<>();
        Set<TreeNode> result = new HashSet<>();
        find(memo, result, root);
        return new ArrayList<>(result);
    }

    public String find(Map<String, Integer> memo, Set<TreeNode> result, TreeNode node) {
        if (node == null) {
            return "#";
        }
        String left = find(memo, result, node.left);
        String right = find(memo, result, node.right);
        String str = left + "," + right + "," + node.val;
        if (memo.getOrDefault(str, 0) == 1) {
            result.add(node);
        }
        memo.put(str, memo.getOrDefault(str, 0) + 1);
        return str;
    }

    @org.junit.Test
    public void findDuplicateSubtreesTest(){
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(5);
        node1.left=node2;
        node1.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        List<TreeNode> treeNodeList = findDuplicateSubtrees(node1);
        System.out.println(treeNodeList);
    }

    /**
     * 是否可以将非空正整数数组分割成两个子集，使得两个子集的元素和相等。
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum = sum / 2;
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum];
    }

    /**
     * 是否可以将非空正整数数组分割成两个子集，使得两个子集的元素和相等。
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum = sum / 2;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i - 1] >= 0) {
                    dp[j] = dp[j] || dp[j - nums[i - 1]];
                }
            }
        }
        return dp[sum];
    }

    /**
     * 硬币凑数（同一面值不限个数）
     * @param amount 目标金额
     * @param coins 硬币面值
     * @return
     */
    public int change(int amount, int[] coins) {

        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 硬币凑数（同一面值不限个数）
     * @param amount 目标金额
     * @param coins 硬币面值
     * @return
     */
    public int change2(int amount, int[] coins) {

        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (j - coins[i - 1] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amount];
    }

    /**
     * 两个字符串变为相同的最少操作（递归实现）
     * @param s1
     * @param s2
     * @return
     */
    public int minDistance(String s1, String s2) {
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();
        Integer[][] memo = new Integer[s1Array.length][s2Array.length];
        return minSub(s1Array, s2Array, s1Array.length - 1, s2Array.length - 1, memo);
    }

    public int minSub(char[] s1Array, char[] s2Array, int i, int j, Integer[][] memo) {

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
     * @param s1
     * @param s2
     * @return
     */
    public int minDistance2(String s1, String s2) {
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

    int result = 0;

    /**
     * 目标和（回溯方法）
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        backUpSum(nums, 0, S);
        return result;
    }

    public void backUpSum(int[] nums, int i, int target) {
        if (nums.length == i) {
            if (target == 0) {
                result++;
            }
            return;
        }
        //+
        target = target - nums[i];
        backUpSum(nums, i + 1, target);
        target = target + nums[i];

        //-
        target = target + nums[i];
        backUpSum(nums, i + 1, target);
        target = target - nums[i];
    }

    @org.junit.Test
    public void findTargetSumWaysTest() {
        int[] nums = {1,2,3,4,5};
        int S = 15;
        findTargetSumWays(nums,S);
        System.out.println(result);
    }

    Map<String, Integer> memo = new HashMap<>();

    /**
     * 目标和（递归方法）
     * @param nums
     * @param i
     * @param target
     * @return
     */
    public int backUpSum2(int[] nums, int i, int target) {
        if (nums.length == i) {
            if (target == 0) {
                return 1;
            }
            return 0;
        }
        String key = i + "," + target;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int result = backUpSum2(nums, i + 1, target - nums[i]) + backUpSum2(nums, i + 1, target + nums[i]);
        memo.put(key, result);
        return result;
    }

    /**
     * 最长递增子序列长度
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
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
    public int maxEnvelopes(int[][] envelopes) {
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

    /**
     * 最大子序和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        int[] dp = new int[nums.length];

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
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
     *  最长回文子序列
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
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

    @org.junit.Test
    public void longestPalindromeSubseqTest(){
        System.out.println(longestPalindromeSubseq("abdffaadwdba"));
    }

    /**
     * 编辑距离问题
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance3(String word1, String word2) {
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

    /**
     * 两个字符串的最小ASCII删除和
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
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

    public int sum(char[] array, int index) {
        int sum = 0;
        for (int i = 0; i < index; i++) {
            sum = sum + array[i];
        }
        return sum;
    }

    /**
     * 最大无重叠区间数
     * @param intervals
     * @return
     */
    public int intervalSchedule(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, Comparator.comparing((a -> a[1])));
        int x_end = intervals[0][1];
        int count = 1;
        for (int[] a : intervals) {
            int start = a[0];
            if (start >= x_end) {
                count++;
                x_end = a[1];
            }
        }
        return count;
    }

    /**
     * 给出n个区间[L,R]，要求删除最少的区间，使得任意两个区间都没有重叠部分
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        return intervals.length - intervalSchedule(intervals);
    }

    /**
     * 用最少的箭头射爆气球
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, Comparator.comparing((a -> a[1])));
        int x_end = points[0][1];
        int count = 1;
        for (int[] a : points) {
            int start = a[0];
            if (start > x_end) {
                count++;
                x_end = a[1];
            }
        }
        return count;
    }

    /**
     * 跳跃游戏（返回是否可以跳跃到底）
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

    /**
     * 是否匹配
     * @param s 字符串
     * @param p 正则表达式
     * @return
     */
    public boolean isMatch(String s, String p) {
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

    Map<String, Boolean> memoMatch = new HashMap<>();

    /**
     * 四否配置
     * @param s 字符串
     * @param p 正则表达式
     * @return
     */
    public boolean isMatch2(String s, String p) {

        return dp(0, 0, s, p);

    }

    public boolean dp(int i, int j, String s, String p) {
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
     * 戳气球
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        int[] point = new int[nums.length + 2];
        point[0] = point[nums.length + 1] = 1;
        for (int i = 1; i < point.length - 1; i++) {
            point[i] = nums[i - 1];
        }
        int[][] dp = new int[point.length][point.length];
        for (int i = point.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < point.length; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + point[k] * point[i] * point[j]);
                }
            }
        }
        return dp[0][point.length - 1];
    }

    public int stoneGame(int[] piles) {
//        Pair[][] dp = new Pair[piles.length][piles.length];
        return 0;
    }

    /**
     * 删除平衡二叉树上节点
     * @param root
     * @param deleteNode
     * @return
     */
    public TreeNode deleteNode(TreeNode root, TreeNode deleteNode) {
        if (root == null) {
            return null;
        }
        if (root.val == deleteNode.val) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode leftMaxNode = getMax(root.left);
            root.val = leftMaxNode.val;
            root.left = deleteNode(root.left, leftMaxNode);
        } else if (root.val > deleteNode.val) {
            root.left = deleteNode(root.left, deleteNode);
        } else if (root.val < deleteNode.val) {
            root.right = deleteNode(root.right, deleteNode);
        }
        return root;
    }

    /**
     *        5
     *      3   7
     *    2  4 6  8
     */
    @org.junit.Test
    public void deleteNodeTest() {
        TreeNode root = new TreeNode(5);
        TreeNode deleteNode = new TreeNode(2);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(8);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        TreeNode newNode = deleteNode(root, deleteNode);
        System.out.println(newNode);
    }

    public TreeNode getMax(TreeNode node) {
        while (node.right!=null){
            node = node.right;
        }
        return node;
    }

    /**
     * 树的反序列化（前序）
     * @param tree
     * @return
     *        1
     *     2     3
     *    # 4  # #
     *     # #
     *    12#4##3##
     */
    public TreeNode deserialize(String tree) {
        LinkedList linkedList = new LinkedList();
        for (Character node : tree.toCharArray()) {
            linkedList.add(node);
        }
       return deserialize(linkedList);
    }

    public TreeNode deserialize(LinkedList linkedList) {
        if (linkedList == null) {
            return null;
        }
        Character root = (Character) linkedList.removeFirst();
        if (!root.equals('#')) {
            TreeNode treeNode = new TreeNode(Integer.valueOf(root.toString()));
            treeNode.left = deserialize(linkedList);
            treeNode.right = deserialize(linkedList);
            return treeNode;
        } else {
            return null;
        }
    }

    @org.junit.Test
    public void deserializeTest() {
        TreeNode root = deserialize("12#4##3##");
        System.out.println(root);
    }

    /**
     * 树的反序列化（后序）
     * @param tree
     * @return
     *        1
     *     2     3
     *    # 4  # #
     *     # #
     *    ###42##31
     */
    public TreeNode deserialize2(String tree) {
        LinkedList linkedList = new LinkedList();
        for (Character node : tree.toCharArray()) {
            linkedList.add(node);
        }
        return deserialize2(linkedList);
    }

    @org.junit.Test
    public void deserialize2Test() {
        TreeNode root = deserialize2("###42##31");
        System.out.println(root);
    }

    public TreeNode deserialize2(LinkedList linkedList) {
        if (linkedList == null) {
            return null;
        }
        Character last = (Character) linkedList.removeLast();
        if (!last.equals('#')) {
            TreeNode treeNode = new TreeNode(Integer.valueOf(last.toString()));
            treeNode.right = deserialize2(linkedList);
            treeNode.left = deserialize2(linkedList);
            return treeNode;
        } else {
            return null;
        }
    }

    /**
     * 滑动窗口最大值
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                queue.push(nums[i]);
            } else {
                queue.push(nums[i]);
                result[i - (k - 1)] = queue.max();
                queue.pop(i - (k - 1));
            }
        }
        return result;
    }
    @org.junit.Test
    public void maxSlidingWindowTest() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(maxSlidingWindow(nums,k));
    }

    HashMap<String, List<Integer>> memoDiffWaysToCompute = new HashMap<>();

    /**
     * 为运算表达式设计优先级
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        if (memoDiffWaysToCompute.containsKey(input)) {
            return memoDiffWaysToCompute.get(input);
        }
        char[] chars = input.toCharArray();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> leftResult = diffWaysToCompute(input.substring(0, i));
                List<Integer> rightResult = diffWaysToCompute(input.substring(i + 1, chars.length));
                leftResult.forEach(left -> {
                    rightResult.forEach(right -> {
                        if (c == '+') {
                            result.add(left + right);
                        } else if (c == '-') {
                            result.add(left - right);
                        } else if (c == '*') {
                            result.add(left * right);
                        }
                    });
                });
            }
        }
        if (result.isEmpty()) {
            result.add(Integer.valueOf(input));
        }
        memoDiffWaysToCompute.put(input, result);
        return result;
    }
    @org.junit.Test
    public void diffWaysToComputeTest(){
        System.out.println(diffWaysToCompute("1+2*3+1"));
    }

    /**
     * 寻找第K大数（二叉堆解法）
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        if (nums.length < 1 || k < 1) {
            return 0;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.peek();
    }

    @org.junit.Test
    public void findKthLargest1Test() {
        int[] nums = {1,2,3,4,5,6,7,8,9,0};
        int k =1;
        System.out.println(findKthLargest1(nums,k));
    }

    /**
     * 寻找第K大数（快速选择解法）
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {

        int left =0;
        int right =nums.length-1;

        while(left<=right){
            int p = partition(nums, left, right);
            if (p == nums.length - k) {
                return nums[p];
            }else if(p>nums.length - k){
                right = p-1;
            }else{
                left = p+1;
            }
        }
        return -1;
    }

    /**
     * 计算器
     *
     * @param s
     * @return
     */
    public int calculator(String s) {
        char[] chars = s.toCharArray();
        Queue<Character> queue = new LinkedList();
        for (int i = 0; i < chars.length; i++) {
            queue.add(chars[i]);
        }
        return calculatorSub(queue);
    }

    public int calculatorSub(Queue<Character> queue){
        Stack<Integer> stack = new Stack();
        int num = 0;
        char sign = '+';
        while(!queue.isEmpty()){
            char sub = queue.poll();
            if (sub == ' ') {
                continue;
            }
            if (isNum(sub)) {
                num = 10 * num + (sub - '0');
            }
            if(sub == '('){
                num =calculatorSub(queue);
            }
            if (!isNum(sub) || queue.isEmpty()) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop()*num);
                        break;
                    case '/':
                        stack.push(stack.pop()/num);
                        break;
                }
                sign = sub;
                num = 0;
            }
            if(sub == ')'){
                break;
            }
        }
        int result = 0;
        while (!stack.empty()) {
            result = result + stack.pop();
        }
        return result;
    }

    private boolean isNum(char s) {
        return s >= '0' && s <= '9';
    }

    @org.junit.Test
    public void calculatorTest() {
        System.out.println(calculator("(2 + 22)/2- 3+4"));
    }

    /**
     * 最长回文子串
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
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

    public String palindrome(char[] array, int l, int r) {
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

    @org.junit.Test
    public void longestPalindromeTest() {
        System.out.println(longestPalindrome("wyeyrurye123"));
    }

    /**
     * 删除有序数据的重复元素
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
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

    @org.junit.Test
    public void removeDuplicatesTest(){
        int[] nums = {1,2,3,3,4,5,6,6,7,8,8,8};
        removeDuplicates(nums);
    }

    @org.junit.Test
    public void findKthLargest2Test() {
        int[] nums = {1,2,3,4,5,6,7,8,9,0};
        int k =2;
        System.out.println(findKthLargest2(nums,k));
    }

    public int[] quickSort(int[] nums){
        sort(nums,0,nums.length-1);
        return nums;
    }
    @org.junit.Test
    public void quickSortTest() {
        int[] nums = {0,1,2,3,4,5,6,7,8,9};
        System.out.println(quickSort(nums));
    }

    public void sort(int[] nums,int left,int right){
        if(left>=right) return;
        int p = partition(nums, left, right);
        sort(nums,left,p-1);
        sort(nums,p+1,right);
    }

    public int partition(int[] nums, int left, int right) {
        if (left == right) return left;
        int p = nums[left];
        while (left < right) {
            while (nums[right] > p && left < right) {
                right--;
            }
            while (nums[left] < p && left < right ) {
                left++;
            }
            if (left >= right) {
                break;
            }
            swap(nums, left, right);
        }
        nums[left] = p;
        return left;
    }
    int partition2(int[] nums, int lo, int hi) {
        if (lo == hi) return lo;
        // 将 nums[lo] 作为默认分界点 pivot
        int pivot = nums[lo];
        // j = hi + 1 因为 while 中会先执行 --
        int i = lo, j = hi + 1;
        while (true) {
            // 保证 nums[lo..i] 都小于 pivot
            while (nums[++i] < pivot) {
                if (i == hi) break;
            }
            // 保证 nums[j..hi] 都大于 pivot
            while (nums[--j] > pivot) {
                if (j == lo) break;
            }
            if (i >= j) break;
            // 如果走到这里，一定有：
            // nums[i] > pivot && nums[j] < pivot
            // 所以需要交换 nums[i] 和 nums[j]，
            // 保证 nums[lo..i] < pivot < nums[j..hi]
            swap(nums, i, j);
        }
        // 将 pivot 值交换到正确的位置
        swap(nums, j, lo);
        // 现在 nums[lo..j-1] < nums[j] < nums[j+1..hi]
        return j;
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }


    @org.junit.Test
    public void test() {
//        System.out.println(minDistance2("horse","ros"));
//        System.out.println(minDistance2("intention", "execution"));
        // dp12 = dp02 dp11 dp11
//        minimumDeleteSum("sea","eat");
//        int[][] intervals = {{1,3},{2,4},{3,6}};
//        System.out.println(intervalSchedule(intervals));
        Assert.assertTrue(isMatch2("ab", ".*"));
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    class Pair {
        public int first;
        public int second;

        public int getFirst() {
            return first;
        }

        public int getSecond() {
            return second;
        }

        public Pair() {
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public void setSecond(int second) {
            this.second = second;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        public ListNode() {

        }
    }

    public class MonotonicQueue{
        LinkedList<Integer> q = new LinkedList<>();
        public void push(int n) {
            // 将小于 n 的元素全部删除
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            // 然后将 n 加入尾部
            q.addLast(n);
        }

        public int max() {
            return q.getFirst();
        }

        public void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }



    @org.junit.Test
    public void MonotonicQueueTest(){
        MonotonicQueue queue = new MonotonicQueue();
        queue.push(1);
        queue.push(2);
        queue.push(5);
        queue.push(3);
        queue.push(6);
        System.out.println(queue);
    }




}
