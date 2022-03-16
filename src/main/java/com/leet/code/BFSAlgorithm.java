/**
 * Copyright (c) 2009-2021 fudai,Inc.All Rights Reserved.
 *
 * @fileName: BFSAlgorithm
 * @package: com.leet.code
 * @date: 2021-08-20 09:38
 * @version: V1.0
 */
package com.leet.code;

import com.common.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @className: BFSAlgorithm
 * @description: 广度优先算法
 * @author: fudai
 * @date: 2021-08-20 09:38
 */
public class BFSAlgorithm {


    /**
     * 树的最小深度
     * @param treeNode
     * @return
     */
    public static int minDepth(TreeNode treeNode) {

        Queue<TreeNode> treeNodeQueue = new LinkedList<>();

        treeNodeQueue.offer(treeNode);
        int step = 1;
        while (!treeNodeQueue.isEmpty()) {
            int size = treeNodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = treeNodeQueue.poll();
                if (curr.left == null && curr.right == null) {
                    return step;
                }
                if (curr.left != null) {
                    treeNodeQueue.add(curr.left);
                }

                if (curr.right != null) {
                    treeNodeQueue.add(curr.right);
                }

            }
            step++;
        }

        return 0;
    }

    /**
     * 打开锁最小次数
     * @param deadends
     * @param target
     * @return
     */
    public static int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList();
        Set<String> visited = new HashSet<>();
        Set<String> deadendSet = new HashSet<>();
        for (String s : deadends) {
            deadendSet.add(s);
        }
        queue.offer("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (deadendSet.contains(curr)) {
                    continue;
                }
                if (curr.equals(target)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    queue.offer(up(curr, j));
                    queue.offer(down(curr, j));
                }
            }
            step++;
        }
        return -1;
    }

    private static String up(String curr, int j) {
        char[] arr = curr.toCharArray();
        if (arr[j] == '9') {
            arr[j] = '0';
        } else {
            arr[j] = (char) (arr[j] + 1);
        }
        return new String(arr);
    }

    private static String down(String curr, int j) {
        char[] arr = curr.toCharArray();
        if (arr[j] == '0') {
            arr[j] = '9';
        } else {
            arr[j] = (char) (arr[j] - 1);
        }
        return new String(arr);
    }


}
