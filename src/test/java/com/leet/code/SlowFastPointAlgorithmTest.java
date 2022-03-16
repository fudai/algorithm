package com.leet.code;

import com.common.ListNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: SlowFastPointAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-17 09:46
 */
public class SlowFastPointAlgorithmTest {

    @Test
    public void removeDuplicates() {

        System.out.println(SlowFastPointAlgorithm.removeDuplicates(new int[]{1,2,3,3,4,4,5}));

    }

    @Test
    public void deleteDuplicates() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        System.out.println(SlowFastPointAlgorithm.deleteDuplicates(node1));
    }

    @Test
    public void removeElement() {

        System.out.println(SlowFastPointAlgorithm.removeElement(new int[]{1,2,3,3,4,4,5},3));

    }

    @Test
    public void moveZeroes() {
        int[] nums = new int[]{1,2,0,0,4,4,5};
        SlowFastPointAlgorithm.moveZeroes(nums);
    }
}