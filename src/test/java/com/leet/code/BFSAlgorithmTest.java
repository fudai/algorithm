package com.leet.code;

import com.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: BFSAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-20 09:56
 */
public class BFSAlgorithmTest {

    @Test
    public void minDepth() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode.left =treeNode2;
        System.out.println(BFSAlgorithm.minDepth(treeNode));
    }
}