/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: Node
 * @package: com.common
 * @date: 2021-07-15 18:40
 * @version: V1.0
 */
package com.common;


/**
 * @className: Node
 * @description: 带next指针的二叉树节点
 * @author: fudai
 * @date: 2021-07-15 18:40
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
