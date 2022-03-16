package com.leet.code;

import com.common.ListNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: ListNodeAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-19 09:47
 */
public class ListNodeAlgorithmTest {

    @Test
    public void isPalindrome() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(2);
        ListNode head5 = new ListNode(1);
        head.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
        System.out.println(ListNodeAlgorithm.isPalindrome(null));
    }

    @Test
    public void isPalindrome2() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(2);
        ListNode head5 = new ListNode(1);
        head.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
        System.out.println(ListNodeAlgorithm.isPalindrome2(head));
    }

    @Test
    public void isPalindrome3() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(2);
        ListNode head4 = new ListNode(2);
//        ListNode head5 = new ListNode(1);
        head.next=head2;
        head2.next=head3;
        head3.next=head4;
//        head4.next=head5;
        System.out.println(ListNodeAlgorithm.isPalindrome3(head));
    }

    @Test
    public void reverse() {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        head.next=head2;
        head2.next=head3;
        head3.next=head4;
        head4.next=head5;
        System.out.println(ListNodeAlgorithm.reverse(head));
    }
}