package tree;

/* 145. Binary Tree Postorder Traversal
Description:
Given a binary tree, return the postorder traversal of its nodes' values.

Example:
Input: [1,null,2,3]
Output: [3,2,1]

Follow up: Recursive solution is trivial, could you do it iteratively?

Analysis:
Time Complexity: O(n)
Space Complexity: O(n)
 */

import common.TreeNode;
import java.util.Stack;
import java.util.List;
import java.util.LinkedList;

public class BinaryTreePostorderTraversal {
    // Iterative Solution:
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            TreeNode node = st.pop();
            res.addFirst(node.val);
            if (node.left != null) {
                st.push(node.left);
            }
            if (node.right != null) {
                st.push(node.right);
            }
        }
        return res;
    }

    // Recursive Solution:
    public List<Integer> postorderRecursiveTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        postHelper(root, res);
        return res;
    }

    public void postHelper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postHelper(root.left, res);
        postHelper(root.right, res);
        res.add(root.val);
    }
}
