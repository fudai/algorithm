package com.leet.code;

import com.common.ListNode;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @className: LinkedListAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-18 09:51
 */
public class LinkedListAlgorithmTest {

    @Test
    public void reverse2() {
    }

    @Test
    public void reverseN() {
    }

    @Test
    public void reverseN2() {
    }

    @Test
    public void reverseMN() {
    }

    @Test
    public void reverseMN2() {
    }

    @Test
    public void reverseKGroup() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
        int k = 6;
        System.out.println(LinkedListAlgorithm.reverseKGroup(head,k));
    }

    @Test
    public void isPalindrome() {
    }

    @Test
    public void traverse() {
    }

    @Test
    public void isPalindrome2() {
    }

    @Test
    public void intersectionNode() {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode anode2    = new ListNode(3);
        ListNode anode3    = new ListNode(5);
        ListNode bnode2    = new ListNode(4);
        ListNode common1    = new ListNode(6);
        ListNode common2    = new ListNode(7);
        a.next=anode2;
        anode2.next=anode3;
        anode3.next=common1;
        common1.next=common2;
        b.next=bnode2;
        bnode2.next=common1;
        System.out.println(LinkedListAlgorithm.intersectionNode(a,b));

//        Collections.checkedList();
    }

}