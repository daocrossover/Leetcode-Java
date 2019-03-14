package tree;

/* 285. Inorder Successor in BST
Description:
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
Note: If the given node has no in-order successor in the tree, return null.
 */

/* Solution:
1. if p has right subtree, then its successor is just the leftmost child of its right subtree
2. if p has no right subtree:
we start from the root, each time we see a node with val larger than p.val,
we know this node may be p's successor. So we record it in suc.
Then we try to move to the next level of the tree:
(1) if p.val > root.val, which means p is in the right subtree, then its successor is also in the right subtree,
so we update root = root.right;
(2) if p.val < root.val, we update root = root.left similarly
once we find p.val == root.val, we know we've reached at p and the current suc is just its successor.
 */

import common.TreeNode;

public class InorderSuccessorInBST {
    TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            return leftMost(p.right);
        }
        TreeNode suc = null;
        while (root != null) {
            if (p.val < root.val) {
                suc = root;
                root = root.left;
            } else if (p.val > root.val) {
                root = root.right;
            } else {
                break;
            }
        }
        return suc;
    }

    private TreeNode leftMost(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
