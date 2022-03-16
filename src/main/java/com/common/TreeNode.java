/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: TreeNode
 * @package: com.common
 * @date: 2021-07-15 18:00
 * @version: V1.0
 */
package com.common;

import lombok.ToString;

/**
 * @className: TreeNode
 * @description: 二叉树节点
 * @author: fudai
 * @date: 2021-07-15 18:00
 */
@ToString
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

}
