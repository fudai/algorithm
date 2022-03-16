package com.leet.code;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: ArrayTest
 * @description:
 * @author: fudai
 * @date: 2021-10-25 21:29
 */
public class ArrayTest {

    @Test
    public void reverse() {
        int[] array = new int[]{2,2,2,0,1};
        System.out.println(Array.reverse(array));
    }

    @Test
    public void search() {
        int[] array = new int[]{3,4,5,1,2};
        System.out.println(Array.search(array));
    }
}