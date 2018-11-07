package tree;

/* 94. Binary Tree Inorder Traversal
Description:
Given a binary tree, return the inorder traversal of its nodes' values.

Example:
Input: [1,null,2,3]
   1
    \
     2
    /
   3
Output: [1,3,2]

Follow up: Recursive solution is trivial, could you do it iteratively?

Analysis:
Time Complexity: O(n)
Space Complexity: O(n)
 */

import common.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal {
    // Iterative Solution:
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        TreeNode cur = root;
        while (!st.empty() || cur != null) {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }

    // Recursive Solution:
    public List<Integer> inorderRecursiveTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        inHelper(root, res);
        return res;
    }

    public void inHelper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inHelper(root.left, res);
        res.add(root.val);
        inHelper(root.right, res);
    }
}
