/**
 * Copyright (c) 2009-2020 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: Test
 * @package: PACKAGE_NAME
 * @date: 2020-09-07 18:23
 * @version: V1.0
 */

import com.common.ListNode;
import com.common.MonotonicQueue;
import com.common.Node;
import com.common.TreeNode;
import org.junit.Assert;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

import static com.leet.code.CalculatorAlgorithm.calculator;
import static com.leet.code.CalculatorAlgorithm.diffWaysToCompute;
import static com.leet.code.FindKthLargestAlgorithm.*;
import static com.leet.code.LinkedListAlgorithm.*;
import static com.leet.code.SlidingWindowAlgorithm.maxSlidingWindow;
import static com.leet.code.SlowFastPointAlgorithm.removeDuplicates;
import static com.leet.code.StringAlgorithm.*;
import static com.leet.code.SumAlgorithm.*;
import static com.leet.code.TreeAlgorithm.*;

/**
 * @className: Test
 * @description:
 * @author: fudai
 * @date: 2020-09-07 18:23
 */
public class Test {

    private static AtomicInteger atomicInt = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<Integer>(100, 0);

    public static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInt.compareAndSet(100, 101);
                atomicInt.compareAndSet(101, 100);
            }
        });

        Thread intT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean c3 = atomicInt.compareAndSet(100, 101);
                System.out.println(c3);        //true
            }
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();

        Thread refT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedRef.compareAndSet(100, 101,
                        atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
                atomicStampedRef.compareAndSet(101, 100,
                        atomicStampedRef.getStamp(), atomicStampedRef.getStamp()+1);
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedRef.getStamp();
                System.out.println("before sleep : stamp = " + stamp);    // stamp = 0
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after sleep : stamp = " + atomicStampedRef.getStamp());//stamp = 1
                boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp+1);
                System.out.println(c3);        //false
            }
        });

        refT1.start();
        refT2.start();
    }


    @org.junit.Test
    public void minWindowTest() {
        System.out.println(minWindow("1234567890", "135"));
    }


    @org.junit.Test
    public void checkInclusionTest() {
        System.out.println(checkInclusion("1234567890", "56"));
    }


    @org.junit.Test
    public void findAnagramsTest() {
        System.out.println(findAnagrams("1234567890", "68"));
    }


    @org.junit.Test
    public void reverseTest() {
        ListNode head1 = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
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
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        ListNode newNode = reverse2(head1);
        System.out.println(newNode);
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


    @org.junit.Test
    public void isPalindrome2Test() {
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
    public void isPalindromeTest() {
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


    @org.junit.Test
    public void twoSum2Test() {
        int[] nums = {1, 7, 2, 8, 11};
        int target = 10;
        System.out.println(Arrays.toString(twoSum2(nums, target)));
    }


    @org.junit.Test
    public void nSumTest() {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 3, 4, 2};
        System.out.println(nSum(nums, 4, 10));
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


    @org.junit.Test
    public void findDuplicateSubtreesTest() {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        List<TreeNode> treeNodeList = findDuplicateSubtrees(node1);
        System.out.println(treeNodeList);
    }


    @org.junit.Test
    public void findTargetSumWaysTest() {
        int[] nums = {1, 2, 3, 4, 5};
        int S = 15;
        int result = findTargetSumWays(nums, S);
        System.out.println(result);
    }


    @org.junit.Test
    public void longestPalindromeSubseqTest() {
        System.out.println(longestPalindromeSubseq("abdffaadwdba"));
    }







    public int stoneGame(int[] piles) {
//        Pair[][] dp = new Pair[piles.length][piles.length];
        return 0;
    }


    /**
     * 5
     * 3   7
     * 2  4 6  8
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


    @org.junit.Test
    public void deserializeTest() {
        TreeNode root = deserialize("12#4##3##");
        System.out.println(root);
    }


    @org.junit.Test
    public void deserialize2Test() {
        TreeNode root = deserialize2("###42##31");
        System.out.println(root);
    }




    @org.junit.Test
    public void maxSlidingWindowTest() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(maxSlidingWindow(nums, k));
    }




    @org.junit.Test
    public void diffWaysToComputeTest() {
        System.out.println(diffWaysToCompute("1+2*3+1"));
    }



    @org.junit.Test
    public void findKthLargest1Test() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int k = 1;
        System.out.println(findKthLargest1(nums, k));
    }




    @org.junit.Test
    public void calculatorTest() throws Exception {
        System.out.println(calculator("(2 + 22)/2- 3+4"));
    }


    @org.junit.Test
    public void longestPalindromeTest() {
        System.out.println(longestPalindrome("wyeyrurye123"));
    }



    @org.junit.Test
    public void removeDuplicatesTest() {
        int[] nums = {1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 8, 8};
        removeDuplicates(nums);
    }

    @org.junit.Test
    public void findKthLargest2Test() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int k = 2;
        System.out.println(findKthLargest2(nums, k));
    }


    @org.junit.Test
    public void quickSortTest() {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(quickSort(nums));
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




    @org.junit.Test
    public void MonotonicQueueTest() {
        MonotonicQueue queue = new MonotonicQueue();
        queue.push(1);
        queue.push(2);
        queue.push(5);
        queue.push(3);
        queue.push(6);
        System.out.println(queue);
    }


}
