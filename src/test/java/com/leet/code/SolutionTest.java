package com.leet.code;

import com.common.ListNode;
import com.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: SolutionTest
 * @description:
 * @author: fudai
 * @date: 2021-12-08 14:10
 */
public class SolutionTest {

    Solution solution = new Solution();

    @Test
    public void addTwoNumbers() {
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next  = new ListNode(9);
        l1.next.next.next  = new ListNode(9);
        l1.next.next.next.next  = new ListNode(9);
        l1.next.next.next.next .next = new ListNode(9);
        l1.next.next.next.next.next.next  = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next  = new ListNode(9);
        l2.next.next.next  = new ListNode(9);
        System.out.println(solution.addTwoNumbers(l1,l2));
    }

    @Test
    public void findMedianSortedArrays() {
        int[] nums1 = {};
        int[] nums2 = {1};
        System.out.println(solution.findMedianSortedArrays(nums1, nums2));
    }

    @Test
    public void convert() {
        System.out.println(solution.convert("PAYPALISHIRING",3));
    }


    @Test
    public void reverse() {
        System.out.println(solution.reverse(1534236469));
    }

    @Test
    public void myAtoi() {
        System.out.println(solution.myAtoi2("+-12"));
    }

    @Test
    public void threeSum() {
        int[] nums = new int[]{-2,0,0,2,2};
        System.out.println(solution.threeSum(nums));
    }

    @Test
    public void threeSumClosest() {
        int[] nums = new int[]{1,1,1,1};
        System.out.println(solution.threeSumClosest(nums,0));
    }

    @Test
    public void letterCombinations() {
        System.out.println(solution.letterCombinations(""));
    }

    @Test
    public void removeNthFromEnd() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);
        int n =2;
        System.out.println(solution.removeNthFromEnd(head, n));
    }

    @Test
    public void isValid() {
        System.out.println(solution.isValid("}"));
    }


    @Test
    public void generateParenthesis() {
        System.out.println(solution.generateParenthesis(2));
    }

    @Test
    public void merge2Lists() {
        ListNode a = new ListNode(1);
        a.next = new ListNode(3);
        ListNode b = new ListNode(2);
        b.next = new ListNode(4);
        b.next.next = new ListNode(6);
        System.out.println(solution.merge2Lists(a, b));
    }

    @Test
    public void mergeKLists3() {
        ListNode a = null;
        ListNode b = new ListNode(-2);
        b.next = new ListNode(-1);
        b.next.next = new ListNode(-1);
        b.next.next.next = new ListNode(-1);
        ListNode[] listNodes = new ListNode[2];
        listNodes[0]=a;
        listNodes[1]=b;
        System.out.println(solution.mergeKLists3(listNodes));
    }

    @Test
    public void swapPairs() {
        ListNode b = new ListNode(-2);
        b.next = new ListNode(-1);
        b.next.next = new ListNode(1);
//        b.next.next.next = new ListNode(2);

        System.out.println(solution.swapPairs(b));
    }

    @Test
    public void reverseKGroup() {
        ListNode b = new ListNode(1);
        b.next = new ListNode(2);
        b.next.next = new ListNode(3);
        b.next.next.next = new ListNode(4);
        b.next.next.next.next = new ListNode(5);
        b.next.next.next.next.next = new ListNode(6);
        b.next.next.next.next.next.next = new ListNode(7);
        System.out.println(solution.reverseKGroup(b,4));
    }

    @Test
    public void removeDuplicates() {
        System.out.println(solution.removeDuplicates(new int[]{1, 1, 2}));
    }

    @Test
    public void strStr() {
        System.out.println(solution.strStr("abc","c"));
    }

    @Test
    public void divide() {
        System.out.println(solution.divide(2147483647,2));
    }

    @Test
    public void findSubstring() {
        System.out.println(solution.findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));
    }

    @Test
    public void nextPermutation() {
        solution.nextPermutation(new int[]{1,3,2});
    }

    @Test
    public void longestValidParentheses() {
        System.out.println(solution.longestValidParentheses("()(()"));
    }

    @Test
    public void search() {
        System.out.println(solution.search(new int[]{1, 3, 5}, 1));
    }

    @Test
    public void isValidSudoku() {
        char[][] chars = new char[][]{{'8','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        System.out.println(solution.isValidSudoku(chars));
    }

    @Test
    public void countAndSay() {
        System.out.println(solution.countAndSay(4));
    }

    @Test
    public void combinationSum() {
        int[] candidates = new int[]{2,3,6,7};
        int target = 7;
        System.out.println(solution.combinationSum(candidates, target));
    }

    @Test
    public void combinationSum2() {
        int[] candidates = new int[]{10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(solution.combinationSum2(candidates, target));
    }

    @Test
    public void firstMissingPositive() {
        System.out.println(solution.firstMissingPositive(new int[]{0}));
    }

    @Test
    public void firstMissingPositive2() {
        System.out.println(solution.firstMissingPositive2(new int[]{1,1}));
    }

    @Test
    public void multiply() {
        System.out.println(solution.multiply("123","456"));
    }


    @Test
    public void isMatch() {
        System.out.println(solution.isMatch("a", "*a*"));
    }

    @Test
    public void jump() {
        System.out.println(solution.jump(new int[]{0}));
    }

    @Test
    public void permute() {
        System.out.println(solution.permute(new int[]{1,2,3}));
    }

    @Test
    public void rotate() {
        int[][] arr= new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        solution.rotate(arr);
        System.out.println(arr.toString());
    }

    @Test
    public void myPow() {
        System.out.println(solution.myPow(0,1));
    }

    @Test
    public void myPow2() {
        System.out.println(solution.myPow2(0,1));
    }

    @Test
    public void totalNQueens() {
        System.out.println(solution.totalNQueens(4));
    }

    @Test
    public void spiralOrder() {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix = new int[][]{{1,2,3}};
        System.out.println(solution.spiralOrder(matrix));
    }

    @Test
    public void lengthOfLastWord() {
        System.out.println(solution.lengthOfLastWord("   fly me   to   the moon  "));
    }

    @Test
    public void rotateRight() {
        ListNode b = new ListNode(1);
        b.next = new ListNode(2);
        b.next.next = new ListNode(3);
        b.next.next.next = new ListNode(4);
        b.next.next.next.next = new ListNode(5);
        b.next.next.next.next.next = new ListNode(6);
        b.next.next.next.next.next.next = new ListNode(7);
        System.out.println(solution.rotateRight(b,8));
    }

    @Test
    public void plusOne() {
        System.out.println(solution.plusOne(new int[]{1, 2, 3}));
    }

    @Test
    public void addBinary() {
        System.out.println(solution.addBinary("1","111"));
    }

    @Test
    public void simplifyPath() {
        System.out.println(solution.simplifyPath("/home/"));
    }

    @Test
    public void searchMatrix() {
//        int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int[][] matrix = new int[][]{{1,3}};
        int target = 3;
        System.out.println(solution.searchMatrix(matrix, target));
    }

    @Test
    public void combine() {
        System.out.println(solution.combine(4,2));
    }

    @Test
    public void subsets() {
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }

    @Test
    public void exist() {
        char[][] board = new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        System.out.println(solution.exist(board, word));
    }

    @Test
    public void removeDuplicates2() {
        System.out.println(solution.removeDuplicates2(new int[]{1,1,1,2,2,3}));
    }

    @Test
    public void search2() {
        System.out.println(solution.search2(new int[]{3,1},1));
    }

    @Test
    public void numDecodings() {
        System.out.println(solution.numDecodings("100"));
    }

    @Test
    public void restoreIpAddresses() {
        System.out.println(solution.restoreIpAddresses("101023"));
    }

    @Test
    public void generateTrees() {
        System.out.println(solution.generateTrees(3));
    }

    @Test
    public void numTrees() {
        System.out.println(solution.numTrees(18));
    }


    /**
     *       3
     *     1   4
     *        2
     */
    @Test
    public void recoverTree() {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left =new TreeNode(1);
        treeNode.right = new TreeNode(4);
        treeNode.right.left = new TreeNode(2);
        solution.recoverTree(treeNode);
    }
}