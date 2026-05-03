package com.dipdeveloper.epam.models;

/**
 * Standard Binary Tree Node.
 * Used across Q8 (Level Order Traversal) and Q9 (BST Validate).
 */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
