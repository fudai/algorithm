package com.leet.code;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: BackpackAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-12 19:26
 */
public class BackpackAlgorithmTest {

    @Test
    public void backpack() {
        System.out.println(BackpackAlgorithm.backpack(4,new int[]{2, 1, 3},new int[]{4, 2, 3}));
    }
}