package tree;

/* 113. Path Sum II
Description:
Given a binary tree and a sum, find all root-to-leaf paths
where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:
Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:
[
   [5,4,11,2],
   [5,8,4,5]
]
 */

import common.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        helper(root, sum, new ArrayList<Integer>(), res);
        return res;
    }

    private void helper(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res) {
        if (root == null) return;
        cur.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            res.add(new ArrayList<Integer>(cur));
        } else {
            helper(root.left, sum - root.val, cur, res);
            helper(root.right, sum - root.val, cur, res);
        }
        cur.remove(cur.size() - 1);
    }
}
