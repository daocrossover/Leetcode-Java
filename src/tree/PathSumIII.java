package tree;

/* 437. Path Sum III
Description:
You are given a binary tree in which each node contains an integer value.
Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards
(traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:
1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */

import common.TreeNode;

import java.util.HashMap;

public class PathSumIII {
    // Solution1: Recursive DFS
    // Time Complexity: O(n^2), Space Complexity: O(n)
    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return dfs(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    public int dfs(TreeNode root, int sum) {
        int res = 0;
        if (root == null) {
            return res;
        }
        if (sum == root.val) {
            res++;
        }
        res += dfs(root.left, sum - root.val);
        res += dfs(root.right, sum - root.val);
        return res;
    }

    // Solution2: PreSum + HashMap
    // Time Complexity: O(n)
    public int pathSum1(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        helper(root, 0, sum, preSum);
        return count;
    }
    int count = 0;
    public void helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return;
        }

        currSum += root.val;
        // currSum - target == prefix, if prefix is in the HashMap,
        // we can find a path summing up to target
        if (preSum.containsKey(currSum - target)) {
            count += preSum.get(currSum - target);
        }
        // The prefix stores the sum from the root to the current node in the recursion
        // The map stores <prefix sum, frequency> pairs before getting to the current node.
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);

        helper(root.left, currSum, target, preSum);
        helper(root.right, currSum, target, preSum);
        // restore the map, as the recursion goes from the bottom to the top
        preSum.put(currSum, preSum.get(currSum) - 1);
    }
}
