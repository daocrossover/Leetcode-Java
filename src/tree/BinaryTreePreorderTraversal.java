package tree;

/* 144. Binary Tree Preorder Traversal
Description:
Given a binary tree, return the preorder traversal of its nodes' values.

Example:
Input: [1,null,2,3]
   1
    \
     2
    /
   3
Output: [1,2,3]

Follow up: Recursive solution is trivial, could you do it iteratively?

Analysis:
Time Complexity: O(n)
Space Complexity: O(n)
 */

import common.TreeNode;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;

public class BinaryTreePreorderTraversal {
    // Iterative Solution:
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            TreeNode node = st.pop();
            res.add(node.val);
            if (node.right != null) st.push(node.right);
            if (node.left != null) st.push(node.left);
        }
        return res;
    }

    // Recursive Solution:
    public List<Integer> preorderRecursiveTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        preHelper(root, res);
        return res;
    }

    public void preHelper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preHelper(root.left, res);
        preHelper(root.right, res);
    }
}
