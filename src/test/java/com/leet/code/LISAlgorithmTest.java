package com.leet.code;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: LISAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-12 09:52
 */
public class LISAlgorithmTest {

    @Test
    public void maxEnvelopes() {

        int[][] envelopes = {{1,2},{1,3},{1,4}};

        System.out.println(LISAlgorithm.maxEnvelopes(envelopes));

    }
}