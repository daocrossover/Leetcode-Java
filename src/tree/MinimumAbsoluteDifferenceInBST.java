package tree;

/* 530. Minimum Absolute Difference in BST
Description:
Given a binary search tree with non-negative values,
find the minimum absolute difference between values of any two nodes.

Example:
Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

Note: There are at least two nodes in this BST.
 */

import common.TreeNode;

import java.util.TreeSet;

public class MinimumAbsoluteDifferenceInBST {
    // Solution1: In-Order Traversal using BST property
    private int min = Integer.MAX_VALUE;
    private Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return min;
        }
        getMinimumDifference(root.left);
        if (prev != null) {
            min = Math.min(min, root.val - prev);
        }
        prev = root.val;
        getMinimumDifference(root.right);
        return min;
    }

    // Solution2:
    // Follow up: What if it's not a BST
    // The idea is to put values in a TreeSet and then every time
    // we can use O(lgN) time to lookup for the nearest values
    TreeSet<Integer> set = new TreeSet<>();
    private int minDiff = Integer.MAX_VALUE;

    public int getMinimumDifference1(TreeNode root) {
        if (root == null) {
            return minDiff;
        }
        if (!set.isEmpty()) {
            if (set.floor(root.val) != null) {
                minDiff = Math.min(minDiff, root.val - set.floor(root.val));
            }
            if (set.ceiling(root.val) != null) {
                minDiff = Math.min(minDiff, set.ceiling(root.val) - root.val);
            }
        }
        set.add(root.val);
        getMinimumDifference1(root.left);
        getMinimumDifference1(root.right);
        return minDiff;
    }
}
