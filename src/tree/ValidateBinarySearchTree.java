package tree;

/* 98. Validate Binary Search Tree
Description:
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input:
    2
   / \
  1   3
Output: true

Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.
 */

import common.TreeNode;
import java.util.Stack;

public class ValidateBinarySearchTree {
    // Solution1: Recursive Solution
    public boolean isValidBST(TreeNode root) {
        return backtrack(root, null, null);
    }

    private boolean backtrack(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }
        if (low != null && root.val <= low || high != null && root.val >= high) {
            return false;
        }
        return backtrack(root.left, low, root.val) && backtrack(root.right, root.val, high);
    }

    // Solution2: Iterative Solution, Inorder Traversal
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && cur.val <= pre.val) {
                return false;
            }
            pre = cur;
            cur = cur.right;
        }
        return true;
    }
}
