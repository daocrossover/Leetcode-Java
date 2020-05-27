package tree;

/* 124. Binary Tree Maximum Path Sum
Description:
Given a non-empty binary tree, find the maximum path sum.
For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
The path must contain at least one node and does not need to go through the root.

Example 1:
Input: [1,2,3]
       1
      / \
     2   3
Output: 6

Example 2:
Input: [-10,9,20,null,null,15,7]
   -10
   / \
  9  20
    /  \
   15   7
Output: 42
 */

import common.TreeNode;

public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;
        dfs(root);
        return res;
    }

    private int res;
    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        // Max path for parent call of root. This path must
        // include at-most one child of root
        int maxSingle = Math.max(root.val, Math.max(left, right) + root.val);
        // Node under is the root of the max sum path and no ancestors of root are there in max sum path
        int maxTop = Math.max(maxSingle, left + right + root.val);
        res = Math.max(res, maxTop);
        return maxSingle;
    }
}
