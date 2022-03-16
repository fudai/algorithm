/**
 * Copyright (c) 2009-2021 FUDAI,Inc.All Rights Reserved.
 *
 * @fileName: TreeAlgorithm
 * @package: com.leet.code
 * @date: 2021-07-15 18:38
 * @version: V1.0
 */
package com.leet.code;

import com.common.Node;
import com.common.TreeNode;

import java.util.*;

/**
 * @className: TreeAlgorithm
 * @description: 树相关算法
 * @author: fudai
 * @date: 2021-07-15 18:38
 */
public class TreeAlgorithm {
    /**
     * 翻转二叉树
     *
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        reverse(root);
        return root;
    }

    public static void reverse(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        reverse(node.left);
        reverse(node.right);
    }

    /**
     * 二叉树展开为链表
     *
     * @param root
     */
    public static void flatten(TreeNode root) {
        List<TreeNode> result = new ArrayList<>();
        before(root, result);
        for (int i = 0; i < result.size() - 1; i++) {
            TreeNode curr = result.get(i);
            curr.left = null;
            curr.right = result.get(i + 1);
        }
    }

    /**
     * 前序遍历
     *
     * @param node
     * @param result
     */
    public static void before(TreeNode node, List<TreeNode> result) {
        if (node != null) {
            result.add(node);
            before(node.left, result);
            before(node.right, result);
        }
    }

    /**
     * 填充二叉树节点的右侧指针（迭代）
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        Node leftNode = root;
        while (leftNode.left != null) {
            Node begin = leftNode;
            while (begin != null) {
                begin.left.next = begin.right;
                if (begin.next != null) {
                    begin.right.next = begin.next.left;
                }
                begin = begin.next;
            }
            leftNode = leftNode.left;
        }
        return root;
    }

    /**
     * 填充二叉树节点的右侧指针（递归）
     *
     * @param root
     * @return
     */
    public static Node connect2(Node root) {
        if (root == null) return null;
        connectTwoNode(root.left, root.right);
        return root;
    }

    public static void connectTwoNode(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node1.right, node2.left);
        connectTwoNode(node2.left, node2.right);
    }

    /**
     * 构造最大二叉树
     * 1.二叉树的根是数组中的最大元素
     * 2.左子树是通过数组中最大值左边部分构造出的最大二叉树
     * 3.右子树是通过数组中最大值右边部分构造出的最大二叉树
     *
     * @param nums
     * @return
     */
    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    public static TreeNode build(int[] nums, int left, int right) {
        if (nums.length == 0 || left > right) {
            return null;
        }
        int max = 0;
        int key = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] >= max) {
                max = nums[i];
                key = i;
            }
        }
        TreeNode treeNode = new TreeNode(max);
        treeNode.left = build(nums, left, key - 1);
        treeNode.right = build(nums, key + 1, right);
        return treeNode;
    }

    /**
     * 根据前序和中序得出树
     *
     * @param preorder 前序遍历
     * @param inorder  中序遍历
     * @return 树
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildNode(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }

    public static TreeNode buildNode(int[] preorder, int[] inorder, int preleft, int preright, int inleft, int inright, Map<Integer, Integer> inMap) {
        if (preleft > preright || inleft > inright || preleft < 0 || preright < 0 || inleft < 0 || inright < 0) {
            return null;
        }
        int inrootP = inMap.get(preorder[preleft]);
        TreeNode treeNode = new TreeNode(preorder[preleft]);
        treeNode.left = buildNode(preorder, inorder, preleft + 1, preleft + inrootP - inleft, inleft, inrootP - 1, inMap);
        treeNode.right = buildNode(preorder, inorder, preleft + inrootP - inleft + 1, preright, inrootP + 1, inright, inMap);
        return treeNode;
    }

    /**
     * 根据后序和中序得出树
     *
     * @param inorder   中序
     * @param postorder 后序
     * @return 树
     */
    public static TreeNode buildTree2(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildNode2(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1, inMap);
    }

    public static TreeNode buildNode2(int[] inorder, int[] postorder, int inleft, int inright, int postleft, int postright, Map<Integer, Integer> inMap) {
        if (postleft > postright || inleft > inright || postleft < 0 || postright < 0 || inleft < 0 || inright < 0) {
            return null;
        }
        int inrootP = inMap.get(postorder[postright]);
        TreeNode treeNode = new TreeNode(postorder[postright]);
        treeNode.left = buildNode2(inorder, postorder, inleft, inrootP - 1, postleft, postleft + (inrootP - inleft) - 1, inMap);
        treeNode.right = buildNode2(inorder, postorder, inrootP + 1, inright, postleft + (inrootP - inleft), postright - 1, inMap);
        return treeNode;
    }

    /**
     * 根据后序和前序得出树
     *
     * @param preorder  前序
     * @param postorder 后序
     * @return 树
     */
    public static TreeNode buildTree3(int[] preorder, int[] postorder) {
        Map<Integer, Integer> postMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postMap.put(postorder[i], i);
        }
//           1
//        2     3
//      4    5 6  7
//            1,2,4,5,3,6,7
//            4,5,2,6,7,3,1
        return buildNode3(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1, postMap);
    }

    public static TreeNode buildNode3(int[] preorder, int[] postorder, int preleft, int preright, int postleft, int postright, Map<Integer, Integer> postMap) {
        if (postleft > postright || preleft > preright || postleft < 0 || postright < 0 || preleft < 0 || preright < 0) {
            return null;
        }
        int postRootP = postMap.get(preorder[preleft + 1]);
        TreeNode treeNode = new TreeNode(postorder[postright]);
        treeNode.left = buildNode2(preorder, postorder, preleft + 1, preleft + postRootP + 1, postleft, postleft + postRootP, postMap);
        treeNode.right = buildNode2(preorder, postorder, preleft + postRootP + 2, preright, postRootP + 1, postright - 1, postMap);
        return treeNode;
    }


    /**
     * 恢复二叉搜索树（显式中序遍历）
     *
     * @param node
     */
    public static void traverse1(TreeNode node) {
        List<TreeNode> intOrderList = new ArrayList<>();
        inorder(node, intOrderList);
        int x = -1;
        int y = -1;
        for (int i = 0; i < intOrderList.size() - 1; i++) {
            if (intOrderList.get(i).val > intOrderList.get(i + 1).val) {
                if (x == -1) {
                    x = i;
                    y = i + 1;
                } else {
                    y = i + 1;
                }
            }
        }
        recover(node, 2, intOrderList.get(x).val, intOrderList.get(y).val);
    }

    /**
     * 恢复二叉搜索树（隐式中序遍历）
     *
     * @param node
     */
    public static void traverse2(TreeNode node) {
        TreeNode x = null;
        TreeNode y = null;
        inorder2(node, x, y, null);
        int temp = x.val;
        x.val = y.val;
        y.val = temp;
    }


    public static void inorder2(TreeNode node, TreeNode x, TreeNode y, TreeNode pred) {
        if (node == null) return;
        inorder2(node.left, x, y, pred);
        if (pred != null && node.val < pred.val) {
            if (x == null) {
                x = node;
                y = pred;
            } else {
                x = pred;
            }
        }
        pred = node;
        inorder2(node.right, x, y, pred);
    }

    public static void recover(TreeNode node, int count, int x, int y) {
        if (node == null) return;
        if (count == 0) return;
        if ((node.val == x || node.val == y) && count > 0) {
            if (node.val == x) {
                node.val = y;
            } else {
                node.val = x;
            }
            count--;
        }
        recover(node.left, count, x, y);
        recover(node.right, count, x, y);
    }

    /**
     * 中序遍历
     *
     * @param node
     * @param treeNodeList
     */
    public static void inorder(TreeNode node, List<TreeNode> treeNodeList) {
        if (node == null) return;
        inorder(node.left, treeNodeList);
        treeNodeList.add(node);
        inorder(node.right, treeNodeList);
    }

    /**
     * 寻找重复子树
     *
     * @param root
     * @return
     */
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> memo = new HashMap<>();
        Set<TreeNode> result = new HashSet<>();
        find(memo, result, root);
        return new ArrayList<>(result);
    }

    public static String find(Map<String, Integer> memo, Set<TreeNode> result, TreeNode node) {
        if (node == null) {
            return "#";
        }
        String left = find(memo, result, node.left);
        String right = find(memo, result, node.right);
        String str = left + "," + right + "," + node.val;
        if (memo.getOrDefault(str, 0) == 1) {
            result.add(node);
        }
        memo.put(str, memo.getOrDefault(str, 0) + 1);
        return str;
    }

    /**
     * 删除平衡二叉树上节点
     *
     * @param root
     * @param deleteNode
     * @return
     */
    public static TreeNode deleteNode(TreeNode root, TreeNode deleteNode) {
        if (root == null) {
            return null;
        }
        if (root.val == deleteNode.val) {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode leftMaxNode = getMax(root.left);
            root.val = leftMaxNode.val;
            root.left = deleteNode(root.left, leftMaxNode);
        } else if (root.val > deleteNode.val) {
            root.left = deleteNode(root.left, deleteNode);
        } else if (root.val < deleteNode.val) {
            root.right = deleteNode(root.right, deleteNode);
        }
        return root;
    }


    public static TreeNode getMax(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /**
     * 树的反序列化（前序）
     *
     * @param tree
     * @return 1
     * 2     3
     * # 4  # #
     * # #
     * 12#4##3##
     */
    public static TreeNode deserialize(String tree) {
        LinkedList linkedList = new LinkedList();
        for (Character node : tree.toCharArray()) {
            linkedList.add(node);
        }
        return deserialize(linkedList);
    }

    public static TreeNode deserialize(LinkedList linkedList) {
        if (linkedList == null) {
            return null;
        }
        Character root = (Character) linkedList.removeFirst();
        if (!root.equals('#')) {
            TreeNode treeNode = new TreeNode(Integer.valueOf(root.toString()));
            treeNode.left = deserialize(linkedList);
            treeNode.right = deserialize(linkedList);
            return treeNode;
        } else {
            return null;
        }
    }

    /**
     * 树的反序列化（后序）
     *
     * @param tree
     * @return 1
     * 2    3
     * # 4  # #
     * # #
     * ###42##31
     */
    public static TreeNode deserialize2(String tree) {
        LinkedList linkedList = new LinkedList();
        for (Character node : tree.toCharArray()) {
            linkedList.add(node);
        }
        return deserialize2(linkedList);
    }

    public static TreeNode deserialize2(LinkedList linkedList) {
        if (linkedList == null) {
            return null;
        }
        Character last = (Character) linkedList.removeLast();
        if (!last.equals('#')) {
            TreeNode treeNode = new TreeNode(Integer.valueOf(last.toString()));
            treeNode.right = deserialize2(linkedList);
            treeNode.left = deserialize2(linkedList);
            return treeNode;
        } else {
            return null;
        }
    }

    /**
     * 组成的二叉搜索树个数
     *
     * @param n 节点个数
     * @return 二叉搜索树个数
     */
    public static int numTrees(int n) {
        int[][] memo = new int[n + 1][n + 1];
        return count(memo, 1, n);
    }

    public static int count(int[][] memo, int left, int right) {
        if (left >= right) {
            return 1;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int res = 0;
        for (int i = left; i <= right; i++) {
            int lnum = count(memo, left, i - 1);
            int rnum = count(memo, i + 1, right);
            res = res + lnum * rnum;
        }
        memo[left][right] = res;
        return res;
    }

    /**
     * 组成的二叉搜索树
     *
     * @param n
     * @return 所有可能的二叉搜索树
     */
    public static List<TreeNode> genTrees(int n) {
        return gen(1, n);
    }

    public static List<TreeNode> gen(int left, int right) {
        List<TreeNode> result = new ArrayList<>();

        if (left > right) {
            result.add(null);
            return result;
        }
        for (int i = left; i <= right; i++) {
            List<TreeNode> lnodes = gen(left, i - 1);
            List<TreeNode> rnodes = gen(i + 1, right);
            final int k = i;
            lnodes.forEach(lnode -> {
                rnodes.forEach(rnode -> {
                    TreeNode root = new TreeNode(k);
                    root.left = lnode;
                    root.right = rnode;
                    result.add(root);
                });
            });
        }
        return result;
    }

    /**
     * 最近公共祖先
     *
     * @param root 根节点
     * @param p    节点1
     * @param q    节点2
     * @return 最近公共祖先节点
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left != null && right != null) {
            return root;
        }
        return left == null ? right : left;
    }

    /**
     * 判断是否二叉搜索树
     *
     * @param root
     * @return
     */
    public static boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.left.val > root.val) {
            return false;
        }
        if (root.right != null && root.right.val < root.val) {
            return false;
        }
        boolean left = isBST(root.left);
        boolean right = isBST(root.right);
        return left && right;
    }

    public static int sum = 0;

    /**
     * 最大二叉搜索树节点和（前序遍历）
     *
     * @param treeNode
     * @return
     */
    public static int maxSumBST(TreeNode treeNode) {
        trace(treeNode);
        return sum;

    }

    /**
     * 最大二叉搜索树节点和（后续遍历）
     *
     * @param treeNode
     * @return
     */
    public static int maxSumBST2(TreeNode treeNode) {
        trace2(treeNode);
        return sum;
    }

    /**
     * @param treeNode
     * @return 0：是否BST，1：以root为根的二叉树所有节点值之和，2：以root为根的二叉树所有节点中的最小值，3：以root为根的二叉树所有节点中的最大值
     */
    private static int[] trace2(TreeNode treeNode) {
        int[] result = new int[4];
        if (Objects.isNull(treeNode)) {
            result[0] = 1;
            result[1] = 0;
            result[2] = Integer.MAX_VALUE;
            result[3] = Integer.MIN_VALUE;
            return result;
        }
        int[] left = trace2(treeNode.left);
        int[] right = trace2(treeNode.right);

        if (left[0] == 1 && right[0] == 1 && treeNode.val > left[3] && treeNode.val < right[2]) {
            result[0] = 1;
            result[1] = treeNode.val + left[1] + right[1];
            result[2] = Math.min(treeNode.val, left[2]);
            result[3] = Math.max(treeNode.val, right[3]);
            sum = Math.max(result[1], sum);
        } else {
            result[0] = 0;
        }
        return result;
    }

    private static void trace(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        if (isBST(treeNode)) {
            sum = Math.max(sum, sumNode(treeNode));
        }
        trace(treeNode.left);
        trace(treeNode.right);
    }

    /**
     * 节点值和
     *
     * @param treeNode
     * @return
     */
    public static int sumNode(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        if (treeNode.left == null) {
            return treeNode.val + sumNode(treeNode.right);
        }
        if (treeNode.right == null) {
            return treeNode.val + sumNode(treeNode.left);
        }
        return treeNode.val + sumNode(treeNode.left) + sumNode(treeNode.right);
    }

    /**
     * 二叉树是否对称
     *
     * @param treeNode
     * @return
     */
    public static boolean isSymmetry(TreeNode treeNode) {
        return isSymmetrySub(treeNode, treeNode);
    }


    public static boolean isSymmetrySub(TreeNode treeNode1, TreeNode treeNode2) {

        if (treeNode1 == null && treeNode2 == null) {
            return true;
        }
        if (treeNode1 == null || treeNode2 == null) {
            return false;
        }
        if (treeNode1.val != treeNode2.val) {
            return false;
        }
        return isSymmetrySub(treeNode1.left, treeNode2.right) && isSymmetrySub(treeNode1.right, treeNode2.left);
    }





}
