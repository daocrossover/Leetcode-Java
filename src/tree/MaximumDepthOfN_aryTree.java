package tree;

/* 559. Maximum Depth of N-ary Tree
Description:
Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.

For example, given a 3-ary tree:
        1
      / | \
     3  2  4
    / \
   5   6

We should return its max depth, which is 3.

Note:
The depth of the tree is at most 1000.
The total number of nodes is at most 5000.
 */

import common.NAryTreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthOfN_aryTree {
    // Solution1: DFS
    class Solution {
        public int maxDepth(NAryTreeNode root) {
            if (root == null) {
                return 0;
            }
            int maxChild = 0;
            for (NAryTreeNode n : root.children) {
                maxChild = Math.max(maxChild, maxDepth(n));
            }
            return maxChild + 1;
        }
    }

    // Solution2: BFS
    public int maxDepth(NAryTreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<NAryTreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                NAryTreeNode cur = q.poll();
                for (NAryTreeNode n : cur.children) {
                    q.offer(n);
                }
            }
            depth++;
        }
        return depth;
    }
}
