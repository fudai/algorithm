package com.leet.code;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * @className: IntervalAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-26 17:02
 */
public class IntervalAlgorithmTest {

    @Test
    public void findMinArrowShots() {
        int[][] points = new int[][]{{1,2},{2,3},{3,4}};
        Arrays.sort(points, Comparator.comparing((a -> a[1])));
        for(int i=0;i<points.length;i++){
            System.out.println(Arrays.toString(points[i]));
        }
    }
}