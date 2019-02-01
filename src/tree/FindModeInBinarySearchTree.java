package tree;

/* 501. Find Mode in Binary Search Tree
Description:
Given a binary search tree (BST) with duplicates,
find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:
The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.

For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2

return [2].

Note: If a tree has more than one mode, you can return them in any order.
Follow up: Could you do that without using any extra space?
(Assume that the implicit stack space incurred due to recursion does not count).
 */

import common.TreeNode;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindModeInBinarySearchTree {
    private Map<Integer, Integer> map;
    private int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[]{};
        }
        this.map = new HashMap<>();
        inOrder(root);
        List<Integer> list = new LinkedList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == max) {
                list.add(key);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            res[i] = list.get(i);
        }
        return res;
    }

    private void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max = Math.max(max, map.get(root.val));
        if (root.right != null) {
            inOrder(root.right);
        }
    }
}
