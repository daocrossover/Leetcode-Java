package tree;

/* 103. Binary Tree Zigzag Level Order Traversal
Description:
Given a binary tree, return the zigzag level order traversal of its nodes' values.
(ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */

import common.TreeNode;
import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
    // Solution1: Iterative Solution
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int reverse = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; ++i) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (reverse == 1) {
                Collections.reverse(level);
            }
            res.add(level);
            reverse = 1 - reverse;
        }
        return res;
    }

    // Solution2: Recursive Solution
    List<List<Integer>> res = new ArrayList<List<Integer>>();

    public List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) return res;
        helper(root, 0);
        return res;
    }

    private void helper(TreeNode node, int level) {
        if (res.size() == level) {
            res.add(new ArrayList<>());
        }
        if (level % 2 == 0) {
            res.get(level).add(node.val);
        } else {
            res.get(level).add(0, node.val);
        }
        if (node.left != null) helper(node.left, level + 1);
        if (node.right != null) helper(node.right, level + 1);
    }
}
