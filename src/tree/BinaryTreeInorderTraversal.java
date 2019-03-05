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

/* 二叉树中序遍历（非递归）
1.首先建立一个stack，当前节点cur为root；
2.将当前节点cur的左子树上的节点逐个推入栈中，直到没有左儿子；
3.取出栈顶的节点，访问该节点，将cur赋值为该节点的右儿子；
4.不断执行2和3，直到栈为空且当前节点也为空。
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

    private void inHelper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inHelper(root.left, res);
        res.add(root.val);
        inHelper(root.right, res);
    }
}
