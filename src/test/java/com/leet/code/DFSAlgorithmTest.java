package com.leet.code;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: DFSAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-23 16:30
 */
public class DFSAlgorithmTest {


    @Test
    public void permute() {
        System.out.println(DFSAlgorithm.permute(new int[]{1,2,3}));
    }

    @Test
    public void solveNQueens() {
        System.out.println(DFSAlgorithm.solveNQueens(8));

    }

    @Test
    public void subsets() {
        System.out.println(DFSAlgorithm.subsets(new int[]{1,2,3}));

    }

    @Test
    public void combine() {
        System.out.println(DFSAlgorithm.combine(3, 2));

    }

    @Test
    public void canPartitionKSubsets() {
        System.out.println(DFSAlgorithm.canPartitionKSubsets(new int[]{1,2,3,4,5,7},3));
    }

    @Test
    public void canPartitionKSubsets2() {
        System.out.println(DFSAlgorithm.canPartitionKSubsets2(new int[]{1,2,3,4,5,6},3));
    }
}