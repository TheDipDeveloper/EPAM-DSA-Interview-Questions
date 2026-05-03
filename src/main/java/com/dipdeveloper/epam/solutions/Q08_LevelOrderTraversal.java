package com.dipdeveloper.epam.solutions;

import com.dipdeveloper.epam.models.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * ============================================================
 * Q8. BINARY TREE LEVEL ORDER TRAVERSAL (BFS)
 * ============================================================
 * PROBLEM:
 *   Given the root of a binary tree, return the level-order
 *   traversal of its nodes' values (left to right, level by level).
 *
 * EXAMPLE:
 *         3
 *        / \
 *       9   20
 *          /  \
 *         15   7
 *
 *   Output: [[3], [9, 20], [15, 7]]
 *
 * EPAM INTERVIEW TIP:
 *   BFS using a Queue is the canonical approach. EPAM may ask for
 *   DFS-based level order as a follow-up (using recursion + level tracking).
 *   Always clarify: "Do you want the result as list of lists per level?"
 * ============================================================
 */
public class Q08_LevelOrderTraversal {

    // ─────────────────────────────────────────────────────────
    // APPROACH 1: BFS with Queue ← STANDARD & PREFERRED
    // Time  Complexity: O(n)   ← visit every node once
    // Space Complexity: O(n)   ← at most n/2 nodes in queue at leaf level
    //
    // KEY INSIGHT:
    //   At each iteration, snapshot the current queue size = nodes in this level.
    //   Process exactly that many nodes, then next iteration = next level.
    // ─────────────────────────────────────────────────────────
    public List<List<Integer>> levelOrderBFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // number of nodes at current level
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                if (node.left  != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────
    // APPROACH 2: DFS Recursion (Depth-based level tracking)
    // Time  Complexity: O(n)
    // Space Complexity: O(h)  ← h = height of tree (call stack)
    //                           Best: O(log n), Worst: O(n) for skewed tree
    // ─────────────────────────────────────────────────────────
    public List<List<Integer>> levelOrderDFS(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfsHelper(root, 0, result);
        return result;
    }

    private void dfsHelper(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null) return;

        // If this is a new level, create a new list for it
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.val);      // add current node to its level
        dfsHelper(node.left,  level + 1, result);
        dfsHelper(node.right, level + 1, result);
    }

    // ─────────────────────────────────────────────────────────
    // QUICK DEMO
    // ─────────────────────────────────────────────────────────
    public static void main(String[] args) {
        Q08_LevelOrderTraversal solution = new Q08_LevelOrderTraversal();

        //       3
        //      / \
        //     9   20
        //        /  \
        //       15   7
        TreeNode root = new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                        new TreeNode(15),
                        new TreeNode(7)));

        System.out.println("=== Q8: Level Order Traversal ===");
        System.out.println("BFS : " + solution.levelOrderBFS(root));   // [[3], [9, 20], [15, 7]]
        System.out.println("DFS : " + solution.levelOrderDFS(root));   // [[3], [9, 20], [15, 7]]
    }
}
