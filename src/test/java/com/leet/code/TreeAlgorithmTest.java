package com.leet.code;

import com.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @className: TreeAlgorithmTest
 * @description:
 * @author: fudai
 * @date: 2021-08-03 09:52
 */
public class TreeAlgorithmTest {

    @Test
    public void numTrees() {
        System.out.println(TreeAlgorithm.numTrees(3));
    }

    @Test
    public void genTrees() {
        System.out.println(TreeAlgorithm.genTrees(3));
    }

    @Test
    public void isBST() {
        TreeNode treeNode = new TreeNode(4);
        System.out.println(TreeAlgorithm.isBST(treeNode));
    }

    @Test
    public void sumNode() {
        TreeNode treeNode = new TreeNode(4);
        TreeNode left1 = new TreeNode(5);
        TreeNode right1= new TreeNode(6);
        treeNode.left=left1;
        treeNode.right=right1;
        System.out.println(TreeAlgorithm.sumNode(treeNode));
    }

    @Test
    public void maxSumBST() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1= new TreeNode(3);
        treeNode.left=left1;
        treeNode.right=right1;
        TreeNode left2 = new TreeNode(2);
        TreeNode right2= new TreeNode(4);
        left1.left=left2;
        left1.right=right2;
        TreeNode left3 = new TreeNode(2);
        TreeNode right3= new TreeNode(5);
        right1.left = left3;
        right1.right = right3;
        TreeNode left4 = new TreeNode(4);
        TreeNode right4= new TreeNode(6);
        right3.left = left4;
        right3.right = right4;
        System.out.println(TreeAlgorithm.maxSumBST2(treeNode));
    }

    @Test
    public void isSymmetry() {
        TreeNode treeNode = new TreeNode(1);
        TreeNode left1 = new TreeNode(4);
        TreeNode right1= new TreeNode(4);
        treeNode.left=left1;
        treeNode.right=right1;
        TreeNode left2 = new TreeNode(2);
        TreeNode right2= new TreeNode(4);
        left1.left=left2;
        left1.right=right2;
        TreeNode left3 = new TreeNode(4);
        TreeNode right3= new TreeNode(2);
        right1.left = left3;
        right1.right = right3;
//        TreeNode left4 = new TreeNode(4);
//        TreeNode right4= new TreeNode(6);
//        right3.left = left4;
//        right3.right = right4;
        System.out.println(TreeAlgorithm.isSymmetry(treeNode));
    }
}