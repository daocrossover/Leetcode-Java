package tree;

/* 515. Find Largest Value in Each Tree Row
Description:
You need to find the largest value in each row of a binary tree.

Example:
Input:

          1
         / \
        3   2
       / \   \
      5   3   9

Output: [1, 3, 9]
 */

import common.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
    // Solution1: BFS
    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; ++i) {
                TreeNode node = q.poll();
                max = Math.max(max, node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(max);
        }
        return res;
    }

    // Solution2: DFS
    public List<Integer> largestValues1(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(root, res, 0);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res, int depth){
        if (root == null) {
            return;
        }
        if (res.size() == depth) {
            res.add(root.val);
        } else {
            res.set(depth, Math.max(res.get(depth), root.val));
        }
        helper(root.left, res, depth + 1);
        helper(root.right, res, depth + 1);
    }
}
